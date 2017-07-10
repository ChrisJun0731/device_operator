package com.genture.device_operator;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Date;

/**
 * Created by zhuj@genture.com on 2017/6/7.
 */
public class DeviceOperator {

    private static Logger logger = Logger.getLogger(DeviceOperator.class);

    private TCPClient tcpClient = null;
    private DataFrame dataFrame = null;
    private Util util = new Util();
    private Device device = null;
    //设置每个块的大小 由数据域的大小加上帧结构中其他内容的大小 外加数据块号占两个字节
    private final int Block_Size = 5*1024;
    //块的大小对应的帧数据中存储方式
    private final byte[] Block_Size_Buf = new byte[2];

    {
        this.util.int2buf(Block_Size, Block_Size_Buf, 0);
    }

    public DeviceOperator(Device device){
        this.tcpClient = new TCPClient(device);
        this.dataFrame = new DataFrame();
        this.device = device;
    }

    /**
     * 打开屏幕
     */
    public void openScreen(){
        try{
            byte[] data_frame = dataFrame.createDataFrame(0x05,  (byte)0x01);
            this.tcpClient.send(data_frame);
            byte[] result = this.tcpClient.receive();
            if(result[4] == 1){
                logger.info("打开屏幕成功");
            }
            else{
                logger.error("开启屏幕失败!");
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 关闭屏幕
     */
    public void closeScreen(){
        try{
            byte[] data_frame = dataFrame.createDataFrame(0x05,  (byte)0x02);
            this.tcpClient.send(data_frame);
            byte[] result = this.tcpClient.receive();
            if(result[4] == 1){
                logger.info("关闭屏幕成功");
            }
            else{
                logger.error("关闭屏幕失败!");
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 下发文件
     * @param filePath 文件路径
     */
    public void sendFile(String filePath){
        File file = new File(filePath);
        String fileName = file.getName();
        boolean successFlag = sendFileName(fileName);
        if(successFlag == true){
            sendFileContent(filePath);
        }
     }

    /**
     * 发送文件名以及数据块的大小
     * @param fileName 文件名称
     */
    private boolean sendFileName(String fileName){
        byte[] file_name = null;
        try{
            file_name = fileName.getBytes("gbk");
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        byte[] data = new byte[Block_Size_Buf.length + file_name.length];
        System.arraycopy(Block_Size_Buf, 0, data, 0, Block_Size_Buf.length);
        System.arraycopy(file_name, 0, data, Block_Size_Buf.length, file_name.length);
        byte[] data_frame = dataFrame.createDataFrame(0x11, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
         if(result[3] == 0x12 && result[4] == 0){
            logger.info("文件名发送失败");
            return false;
        }
        else if(result[3] == 0x12 && result[4] == 1){
            logger.info("文件名发送成功");
            return true;
        }
        else if(result[3] ==0x12 && result[4] == 2){
            logger.info("文件已存在");
            return false;
        }
        else{
            return false;
        }
    }

    /**
     * 发送文件内容
     * @param filePath 文件路径
     */
    private void sendFileContent(String filePath){
        File file = new File(filePath);
        try{
            InputStream is = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] file_content = new byte[Block_Size];
            int len;
            int block_num = 1;

            boolean last_block_flag = false;
            while((len=bis.read(file_content))!=-1){
                if(len < Block_Size){
                    byte[] last_block = new byte[len];
                    System.arraycopy(file_content, 0, last_block, 0, len);
                    file_content = last_block;
                    last_block_flag = true;
                }
                byte[] block_num_buf = new byte[2];
                util.int2buf(block_num, block_num_buf, 0);
                byte[] data = new byte[block_num_buf.length + file_content.length];
                System.arraycopy(block_num_buf, 0, data, 0, block_num_buf.length);
                System.arraycopy(file_content, 0, data, block_num_buf.length, file_content.length);
                byte[] data_frame = dataFrame.createDataFrame(0x13, data);

                this.tcpClient.send(data_frame);
                byte[] result = this.tcpClient.receive();
                if(result[6] == 1){
                    logger.info("数据块"+block_num+"发送成功！");
                }
                int try_times = 0;
                while(result[6] == 0 && try_times<10){
                    this.tcpClient.send(data_frame);
                    result = this.tcpClient.receive();
                    try_times++;
                }
                if(try_times>=10){
                    logger.error("数据块"+block_num+"发送失败！请检查网络是否连通，程序终止！");
                    return;
                }
                if(last_block_flag){
                    byte[] file_send_result = this.tcpClient.receive();
                    if(util.mapByte2Int(file_send_result[3]) == 0xf9 && file_send_result[4] == 1){
                        logger.info("文件发送成功");
                    }
                    else{
                        logger.error("文件发送失败!请检查文件后缀名是否符合要求！（后缀只能是.lst .jpg .gif .wmv2之一。）");
                    }
                }
                block_num++;
            }
            if(last_block_flag == false){
                byte[] block_num_buf = new byte[2];
                util.int2buf(block_num, block_num_buf, 0);
                //这里的空的数据块是指没有数据块
                byte[] data = new byte[block_num_buf.length];
                System.arraycopy(block_num_buf, 0, data, 0, block_num_buf.length);
                byte[] data_frame = dataFrame.createDataFrame(0x13, data);
                tcpClient.send(data_frame);
                byte[] result = this.tcpClient.receive();
                if(last_block_flag){
                    if(result[14] == 1){
                        logger.info("文件发送成功");
                    }
                    else{
                        logger.error("文件发送失败");
                    }
                }
            }
        }
        catch(Exception e){
//            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取设备状态
     * @return 字节数组
     */
    private byte[] getDeviceState(){
        byte[] data_frame = this.dataFrame.createDataFrame(0x01, null);
        this.tcpClient.send(data_frame);
        return this.tcpClient.receive();
    }

    /**
     * 获取设备日期和时间
     * @return json字符串
     */
    public String getDateAndTime(){
        byte[] result = getDeviceState();
        if(result[3] == 0x02){
            int year = util.buf2int(result[4], result[5]);
            int month = result[6];
            int day = result[7];
            int hour = result[8];
            int minute = result[9];
            int second = result[10];

            logger.info("获取设备时间成功");
            return "{year:"+year+",month:"+month+",day:"+day+",hour:"+hour+",minute:"+minute+",second:"+second+"}";
        }
        else{
            logger.error("设备无响应");
            return null;
        }
    }

    /**
     * 获取屏幕大小
     * @return json字符串
     */
    public String getScreenSize(){
        byte[] data_frame = dataFrame.createDataFrame(0x82, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3]) == 0x83){
            int width = util.buf2int(result[4], result[5]);
            int height = util.buf2int(result[6], result[7]);
            logger.info("获取屏幕大小成功！");
            return "{height:" + height + ",width:"+ width +"}";
        }
        else{
            logger.error("设备无响应");
            return null;
        }
    }

    /**
     * 重启设备
     */
    public void restartDevice(){
        byte[] data_frame = dataFrame.createDataFrame(0x0d, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[4]==0x0e && result[5] == 1){
            logger.info("重启设备成功！");
        }
        else{
            logger.error("重启设备失败!");
        }
    }

    /**
     * 设置关屏温度，即温度达到多少后关屏。0表示不进行关屏处理
     * @param temperature 关屏温度
     */
    public void setCloseScreenTemperature(int temperature){
        byte[] data = new byte[6];
        data[5] = (byte)temperature;
        byte[] data_frame = dataFrame.createDataFrame(0x15, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[3] == 0x16 && result[4] == 1){
            logger.info("设置关屏温度成功！");
        }
        else{
            logger.error("设置关屏温度失败！");
        }
    }

    /**
     * 获得设备关屏温度
     * @return 关屏温度
     * -1 表示查询失败
     */
    public int getCloseScreenTemperature(){
        byte[] data_frame = dataFrame.createDataFrame(0x29, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3])== 0x2a){
            logger.info("查询屏幕报警温度成功："+ result[9]);
            return result[9];
        }
        else{
            logger.error("设备无响应");
            return -1;
        }
    }

    /**
     * 获取机箱温度
     * @return 机箱所处环境温度
     */
    public int getEnvironmentTemperature(){
        byte[] result = getDeviceState();
        int temperature = 0;
        //符号位1表示正数，2表示负数
        int symbol = 1;
        if(util.mapByte2Int(result[3]) == 0x02){
            symbol = result[14];
            temperature = result[15];
            if(symbol == 2){
                temperature = 0 - temperature;
            }
        }
        else{
            logger.error("设备无响应");
        }
        if(symbol == 1){
            logger.info("查询当前环境温度成功,当前环境温度为："+temperature);
        }
        if(symbol == 2){
            logger.info("查询当前环境温度成功,当前环境温度为：-"+temperature);
        }
        return temperature;
    }

    /**
     * 获取设备开关门状态
     * @return
     * open表示打开
     * close表示关闭
     */
    public String getDoorState(){
        byte[] result = getDeviceState();
        if(util.mapByte2Int(result[3]) == 0x02){
            if(result[11] == 1){
                logger.info("门状态查询成功！当前门状态为open");
                return  "open";
            }
            else if(result[11] == 2){
                logger.info("门状态查询成功！当前门状态为close");
                return  "close";
            }
            else{
                 return null;
            }
        }
        else{
            logger.error("设备无响应");
            return null;
        }
    }

    /**
     * 设置虚连接
     * @param open 1 开启虚连接 0 关闭虚连接
     * @param seconds 虚连接检测时间阈值
     * @param play 虚连接状态播放节目
     */
    public void setVirtualConn(int open, int seconds, int play){
        byte[] secondsBuf = new byte[2];
        util.int2buf(seconds, secondsBuf, 0);
        byte[] data = {(byte)open, secondsBuf[0], secondsBuf[1], (byte)play};
        byte[] data_frame = dataFrame.createDataFrame(0xf3, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3]) == 0xf4 && result[4] == 1){
            logger.info("虚连接设置成功！");
        }
        else{
            logger.error("虚连接设置失败！");
        }
    }

    /**
     * 获取屏幕的坏点总数
     * @return 坏点总数
     */
    public int getTotalBadPoint(){
        byte[] data_frame = dataFrame.createDataFrame(0x0b, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        int bad_point_num = 0;
        if(util.mapByte2Int(result[3]) == 0x0c){
            if(result[4]==1){
                bad_point_num = util.buf2int(result[5], result[6]);
                logger.info("点检检测成功!");
            }
            else{
                logger.error("点检操作失败");
            }
        }
        else{
            logger.error("设备无响应");
        }
        return bad_point_num;
    }

    /**
     * 获取屏幕内容
     * @return 字节数组
     */
    @Deprecated
    private byte[] getDisplayContent(){
        byte[] data_frame = dataFrame.createDataFrame(0x2d, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        return result;
    }

    /**
     * 手动设置屏幕亮度
     * @param light 屏幕亮度范围：1-255
     */
    public void setLightByHand(int light){
        if(light<1 || light >255){
            logger.error("设置的亮度范围超出了1-255！");
            return;
        }
        byte[] data = {(byte)2, (byte)light};
        byte[] data_frame = dataFrame.createDataFrame(0x07, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[3] == 0x08 && result[4] == 1){
            logger.info("手动设置屏幕亮度成功！");
        }
        else{
            logger.error("手动设置屏幕亮度失败！");
        }
    }

    /**
     * 删除控制卡上所有文件
     */
    public void clearAllFiles(){
        clearFiles((byte)0);
    }

    /**
     * 删除当前播放列表中文件以外的所有文件
     */
    public void clearInvalidFiles(){
        clearFiles((byte)1);
    }

    /**
     * 清理文件
     */
    private void clearFiles(byte type){
        byte[] data_frame = dataFrame.createDataFrame(0x7c, type);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3])== 0x7d && result[4] == 1){
            if(type == 0){
                logger.info("已清理控制卡上所有文件！");
            }
            else if(type == 1){
                logger.info("已清理控制卡上除当前播放列表的所有文件！");
            }
        }
        else{
            logger.error("执行清理文件操作失败！");
        }
    }

    /**
     * 获取屏幕截图
     */
    public void captureScreen(){
        String path = "E:\\capture";
        String filename = new Date().toString();
        String suffix = ".bmp";
        File file = new File(path+filename+suffix);
        FileOutputStream fos = null;
        if(!file.exists()){
            try{
                file.createNewFile();
                fos = new FileOutputStream(file, true);
            }
            catch(Exception e){
                logger.error(e.getMessage());
            }
        }
        int pack_size = 5*1024;
        byte[] pack_size_buf = new byte[2];
        util.int2buf(pack_size, pack_size_buf, 0);
        byte[] send_data = dataFrame.createDataFrame(0x80, pack_size_buf);
        this.tcpClient.send(send_data);
        while(true){
            byte[] rec_data = this.tcpClient.receive();
            byte[] rec_trans_data = util.decompile(rec_data);
            try{
                fos.write(rec_trans_data, 4, rec_trans_data.length-7);
            }
            catch(Exception e){
                logger.error(e.getMessage());
            }
            if(rec_trans_data.length < pack_size + 7){
                try{
                    fos.close();
                }
                catch(Exception e){
                    logger.error(e.getMessage());
                }
                break;
            }
        }
    }

    /**
     * 指定播放列表进行播放
     * @param num 播放列表编号
     */
    public void playAssignedList(int num){
        byte[] data_frame = dataFrame.createDataFrame(0x1b, (byte)num);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3])== 0x1c){
            if(result[4] == 1){
                logger.info("指定播放列表进行播放成功!");
            }
            else{
                logger.error("指定播放列表播放失败!");
            }
        }
    }

    /**
     * 设置屏体基本参数
     * @param basicParam
     */
    public void setBasicParam(BasicParam basicParam){
        byte[] data = convertBasicParam2Data(basicParam);
        byte[] data_frame = dataFrame.createDataFrame(0x19, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[3] == 0x1a){
            if(result[4] == 1){
                logger.info("设备基本参数设置成功!");
            }
            else{
                logger.error("设备基本参数设置失败!");
            }
        }
        else{
            logger.error("设备无响应!");
        }
    }

    /**
     * 查询设备的基本参数
     * @return
     */
    public BasicParam queryBasicParam(){
        byte[] data_frame = dataFrame.createDataFrame(0x27, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        BasicParam basicParam = new BasicParam();
        if(result[3] == 0x28){
            basicParam = convertData2BasicParam(result);
        }
        else{
            logger.error("设备无响应！");
        }
        return basicParam;
    }

    /**
     * 关闭与设备连接的tcp连接
     */
    public void disconnect(){
        this.tcpClient.close();
    }

    /**
     * 将basicParam对象转换为byte数组
     * @param basicParam
     * @return
     */
    private byte[] convertBasicParam2Data(BasicParam basicParam){
        byte[] data = new byte[26];
        byte reserve1 = 0;
        byte[] reserve2 = new byte[2];

        byte[] screen_num = new byte[2];
        util.int2buf(Integer.parseInt(basicParam.getScreen_number()), screen_num, 0);
        byte[] ip = util.ip2byte(basicParam.getIp());
        byte[] port = new byte[2];
        util.int2buf(Integer.parseInt(basicParam.getPort()), port, 0);
        byte[] subnet_mask = util.ip2byte(basicParam.getSubnet_mask());
        byte[] gateway = util.ip2byte(basicParam.getGateway());
        byte[] upp_com_ip = util.ip2byte(basicParam.getUpp_com_ip());
        byte report = Byte.parseByte(basicParam.getReport());

        System.arraycopy(screen_num, 0, data, 0, 2);
        System.arraycopy(ip, 0, data, 2, 4);
        System.arraycopy(port, 0, data, 6, 2);
        System.arraycopy(subnet_mask, 0, data, 8, 4);
        System.arraycopy(gateway, 0, data, 12, 4);
        System.arraycopy(upp_com_ip, 0, data, 16, 4);
        System.arraycopy(reserve2, 0, data, 20, 2);
        System.arraycopy(report, 0, data, 22, 1);
        System.arraycopy(reserve1, 0, data, 23, 1);
        System.arraycopy(reserve1, 0, data, 24, 1);
        System.arraycopy(reserve1, 0, data, 25, 1);

        return data;
    }

    /**
     * 将byte数组转换为BasicParam对象
     * @param data
     * @return
     */
    private BasicParam convertData2BasicParam(byte[] data){
        byte[] screen_num = new byte[2];
        byte[] ip = new byte[4];
        byte[] port = new byte[2];
        byte[] subnet_mask = new byte[4];
        byte[] gateway = new byte[4];
        byte[] upp_com_ip = new byte[4];
        byte report = 0;

        screen_num[0] = data[5];
        screen_num[1] = data[6];
        ip[0] = data[7];
        ip[1] = data[8];
        ip[2] = data[9];
        ip[3] = data[10];
        port[0] = data[11];
        port[1] = data[12];
        subnet_mask[0] = data[13];
        subnet_mask[1] = data[14];
        subnet_mask[2] = data[15];
        subnet_mask[3] = data[16];
        gateway[0] = data[17];
        gateway[1] = data[18];
        gateway[2] = data[19];
        gateway[3] = data[20];
        upp_com_ip[0] = data[21];
        upp_com_ip[1] = data[22];
        upp_com_ip[2] = data[23];
        upp_com_ip[3] = data[24];
        report = data[27];

        BasicParam basicParam = new BasicParam();
        basicParam.setScreen_number(String.valueOf(util.buf2int(screen_num[0], screen_num[1])));
        basicParam.setIp(util.byte2ip(ip));
        basicParam.setPort(String.valueOf(util.buf2int(port[0], port[1])));
        basicParam.setSubnet_mask(util.byte2ip(subnet_mask));
        basicParam.setGateway(util.byte2ip(gateway));
        basicParam.setUpp_com_ip(util.byte2ip(upp_com_ip));
        basicParam.setReport(String.valueOf(report));

        return basicParam;
    }

}
