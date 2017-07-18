package com.genture.device_operator;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Calendar;
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
    //����ÿ����Ĵ�С ��������Ĵ�С����֡�ṹ���������ݵĴ�С ������ݿ��ռ�����ֽ�
    private final int Block_Size = 5*1024;
    //��Ĵ�С��Ӧ��֡�����д洢��ʽ
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
     * ����Ļ
     */
    public void openScreen(){
        try{
            byte[] data_frame = dataFrame.createDataFrame(0x05,  (byte)0x01);
            this.tcpClient.send(data_frame);
            byte[] result = this.tcpClient.receive();
            if(result[4] == 1){
                logger.info("����Ļ�ɹ�");
            }
            else{
                logger.error("������Ļʧ��!");
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * �ر���Ļ
     */
    public void closeScreen(){
        try{
            byte[] data_frame = dataFrame.createDataFrame(0x05,  (byte)0x02);
            this.tcpClient.send(data_frame);
            byte[] result = this.tcpClient.receive();
            if(result[4] == 1){
                logger.info("�ر���Ļ�ɹ�");
            }
            else{
                logger.error("�ر���Ļʧ��!");
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * �·��ļ�
     * @param filePath �ļ�·��
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
     * �����ļ����Լ����ݿ�Ĵ�С
     * @param fileName �ļ�����
     */
    private boolean sendFileName(String fileName){
        byte[] file_name = null;
        try{
            file_name = fileName.getBytes();
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
            logger.info("�ļ�������ʧ��");
            return false;
        }
        else if(result[3] == 0x12 && result[4] == 1){
            logger.info("�ļ������ͳɹ�");
            return true;
        }
        else if(result[3] ==0x12 && result[4] == 2){
            logger.info("�ļ��Ѵ���");
            return false;
        }
        else{
            return false;
        }
    }

    /**
     * �����ļ�����
     * @param filePath �ļ�·��
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
                    logger.info("���ݿ�"+block_num+"���ͳɹ���");
                }
                int try_times = 0;
                while(result[6] == 0 && try_times<10){
                    this.tcpClient.send(data_frame);
                    result = this.tcpClient.receive();
                    try_times++;
                }
                if(try_times>=10){
                    logger.error("���ݿ�"+block_num+"����ʧ�ܣ����������Ƿ���ͨ��������ֹ��");
                    return;
                }
                if(last_block_flag){
                    byte[] file_send_result = this.tcpClient.receive();
                    if(util.mapByte2Int(file_send_result[3]) == 0xf9 && file_send_result[4] == 1){
                        logger.info("�ļ����ͳɹ�");
                    }
                    else{
                        logger.error("�ļ�����ʧ��!�����ļ���׺���Ƿ����Ҫ�󣡣���׺ֻ����.lst .jpg .gif .wmv2֮һ����");
                    }
                }
                block_num++;
            }
            if(last_block_flag == false){
                byte[] block_num_buf = new byte[2];
                util.int2buf(block_num, block_num_buf, 0);
                //����Ŀյ����ݿ���ָû�����ݿ�
                byte[] data = new byte[block_num_buf.length];
                System.arraycopy(block_num_buf, 0, data, 0, block_num_buf.length);
                byte[] data_frame = dataFrame.createDataFrame(0x13, data);
                tcpClient.send(data_frame);
                byte[] result = this.tcpClient.receive();
                if(last_block_flag){
                    if(result[14] == 1){
                        logger.info("�ļ����ͳɹ�");
                    }
                    else{
                        logger.error("�ļ�����ʧ��");
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
     * ��ȡ�豸״̬
     * @return �ֽ�����
     */
    private byte[] getDeviceState(){
        byte[] data_frame = this.dataFrame.createDataFrame(0x01, null);
        this.tcpClient.send(data_frame);
        return this.tcpClient.receive();
    }

    /**
     * ��ȡ�豸���ں�ʱ��
     * @return json�ַ���
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

            logger.info("��ȡ�豸ʱ��ɹ�");
            return "{year:"+year+",month:"+month+",day:"+day+",hour:"+hour+",minute:"+minute+",second:"+second+"}";
        }
        else{
            logger.error("�豸����Ӧ");
            return null;
        }
    }

    /**
     * ��ȡ��Ļ��С
     * @return json�ַ���
     */
    public String getScreenSize(){
        byte[] data_frame = dataFrame.createDataFrame(0x82, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3]) == 0x83){
            int width = util.buf2int(result[4], result[5]);
            int height = util.buf2int(result[6], result[7]);
            logger.info("��ȡ��Ļ��С�ɹ���");
            return "{height:" + height + ",width:"+ width +"}";
        }
        else{
            logger.error("�豸����Ӧ");
            return null;
        }
    }

    /**
     * �����豸
     */
    public void restartDevice(){
        byte[] data_frame = dataFrame.createDataFrame(0x0d, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[4]==0x0e && result[5] == 1){
            logger.info("�����豸�ɹ���");
        }
        else{
            logger.error("�����豸ʧ��!");
        }
    }

    /**
     * ���ù����¶ȣ����¶ȴﵽ���ٺ������0��ʾ�����й�������
     * @param temperature �����¶�
     */
    public void setCloseScreenTemperature(int temperature){
        byte[] data = new byte[6];
        data[5] = (byte)temperature;
        byte[] data_frame = dataFrame.createDataFrame(0x15, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[3] == 0x16 && result[4] == 1){
            logger.info("���ù����¶ȳɹ���");
        }
        else{
            logger.error("���ù����¶�ʧ�ܣ�");
        }
    }

    /**
     * ����豸�����¶�
     * @return �����¶�
     * -1 ��ʾ��ѯʧ��
     */
    public int getCloseScreenTemperature(){
        byte[] data_frame = dataFrame.createDataFrame(0x29, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3])== 0x2a){
            logger.info("��ѯ��Ļ�����¶ȳɹ���"+ result[9]);
            return result[9];
        }
        else{
            logger.error("�豸����Ӧ");
            return -1;
        }
    }

    /**
     * ��ȡ�����¶�
     * @return �������������¶�
     */
    public int getEnvironmentTemperature(){
        byte[] result = getDeviceState();
        int temperature = 0;
        //����λ1��ʾ������2��ʾ����
        int symbol = 1;
        if(util.mapByte2Int(result[3]) == 0x02){
            symbol = result[14];
            temperature = result[15];
            if(symbol == 2){
                temperature = 0 - temperature;
            }
        }
        else{
            logger.error("�豸����Ӧ");
        }
        if(symbol == 1){
            logger.info("��ѯ��ǰ�����¶ȳɹ�,��ǰ�����¶�Ϊ��"+temperature);
        }
        if(symbol == 2){
            logger.info("��ѯ��ǰ�����¶ȳɹ�,��ǰ�����¶�Ϊ��-"+temperature);
        }
        return temperature;
    }

    /**
     * ��ȡ�豸������״̬
     * @return
     * open��ʾ��
     * close��ʾ�ر�
     */
    public String getDoorState(){
        byte[] result = getDeviceState();
        if(util.mapByte2Int(result[3]) == 0x02){
            if(result[11] == 1){
                logger.info("��״̬��ѯ�ɹ�����ǰ��״̬Ϊopen");
                return  "open";
            }
            else if(result[11] == 2){
                logger.info("��״̬��ѯ�ɹ�����ǰ��״̬Ϊclose");
                return  "close";
            }
            else{
                 return null;
            }
        }
        else{
            logger.error("�豸����Ӧ");
            return null;
        }
    }

    /**
     * ����������
     * @param open 1 ���������� 0 �ر�������
     * @param seconds �����Ӽ��ʱ����ֵ
     * @param play ������״̬���Ž�Ŀ
     */
    public void setVirtualConn(int open, int seconds, int play){
        byte[] secondsBuf = new byte[2];
        util.int2buf(seconds, secondsBuf, 0);
        byte[] data = {(byte)open, secondsBuf[0], secondsBuf[1], (byte)play};
        byte[] data_frame = dataFrame.createDataFrame(0xf3, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3]) == 0xf4 && result[4] == 1){
            logger.info("���������óɹ���");
        }
        else{
            logger.error("����������ʧ�ܣ�");
        }
    }

    /**
     * ��ȡ��Ļ�Ļ�������
     * @return ��������
     */
    public int getTotalBadPoint(){
        byte[] data_frame = dataFrame.createDataFrame(0x0b, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        int bad_point_num = 0;
        if(util.mapByte2Int(result[3]) == 0x0c){
            if(result[4]==1){
                bad_point_num = util.buf2int(result[5], result[6]);
                logger.info("�����ɹ�!");
            }
            else{
                logger.error("������ʧ��");
            }
        }
        else{
            logger.error("�豸����Ӧ");
        }
        return bad_point_num;
    }

    /**
     * ��ȡ��Ļ����
     * @return �ֽ�����
     */
    @Deprecated
    private byte[] getDisplayContent(){
        byte[] data_frame = dataFrame.createDataFrame(0x2d, null);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        return result;
    }

    /**
     * �ֶ�������Ļ����
     * @param light ��Ļ���ȷ�Χ��1-255
     */
    public void setLightByHand(int light){
        if(light<1 || light >255){
            logger.error("���õ����ȷ�Χ������1-255��");
            return;
        }
        byte[] data = {(byte)2, (byte)light};
        byte[] data_frame = dataFrame.createDataFrame(0x07, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[3] == 0x08 && result[4] == 1){
            logger.info("�ֶ�������Ļ���ȳɹ���");
        }
        else{
            logger.error("�ֶ�������Ļ����ʧ�ܣ�");
        }
    }

    /**
     * ɾ�����ƿ��������ļ�
     */
    public void clearAllFiles(){
        clearFiles((byte)0);
    }

    /**
     * ɾ����ǰ�����б����ļ�����������ļ�
     */
    public void clearInvalidFiles(){
        clearFiles((byte)1);
    }

    /**
     * �����ļ�
     */
    private void clearFiles(byte type){
        byte[] data_frame = dataFrame.createDataFrame(0x7c, type);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3])== 0x7d && result[4] == 1){
            if(type == 0){
                logger.info("��������ƿ��������ļ���");
            }
            else if(type == 1){
                logger.info("��������ƿ��ϳ���ǰ�����б�������ļ���");
            }
        }
        else{
            logger.error("ִ�������ļ�����ʧ�ܣ�");
        }
    }

    /**
     * ��ȡ��Ļ��ͼ
     */
    public void captureScreen(){
        File file = util.createTempFile();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(file);
        }
        catch(Exception e){
            e.printStackTrace();
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
                fos.write(rec_trans_data, 6, rec_trans_data.length-7);
            }
            catch(Exception e){
                logger.error(e.getMessage());
            }

            if(rec_trans_data.length < pack_size + 7){
                try{
                    fos.flush();
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
     * ָ�������б���в���
     * @param num �����б���
     */
    public void playAssignedList(int num){
        byte[] data_frame = dataFrame.createDataFrame(0x1b, (byte)num);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(util.mapByte2Int(result[3])== 0x1c){
            if(result[4] == 1){
                logger.info("ָ�������б���в��ųɹ�!");
            }
            else{
                logger.error("ָ�������б���ʧ��!");
            }
        }
    }

    /**
     * ���������������
     * @param basicParam ������������
     */
    public void setBasicParam(BasicParam basicParam){
        byte[] data = convertBasicParam2Data(basicParam);
        byte[] data_frame = dataFrame.createDataFrame(0x19, data);
        this.tcpClient.send(data_frame);
        byte[] result = this.tcpClient.receive();
        if(result[3] == 0x1a){
            if(result[4] == 1){
                logger.info("�豸�����������óɹ�!");
            }
            else{
                logger.error("�豸������������ʧ��!");
            }
        }
        else{
            logger.error("�豸����Ӧ!");
        }
    }

    /**
     * ��ѯ�豸�Ļ�������
     * @return ������������
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
            logger.error("�豸����Ӧ��");
        }
        return basicParam;
    }

    /**
     * �ر����豸���ӵ�tcp����
     */
    public void disconnect(){
        this.tcpClient.close();
    }

    /**
     * ��basicParam����ת��Ϊbyte����
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
     * ��byte����ת��ΪBasicParam����
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

        screen_num[0] = data[4];
        screen_num[1] = data[5];
        ip[0] = data[6];
        ip[1] = data[7];
        ip[2] = data[8];
        ip[3] = data[9];
        port[0] = data[10];
        port[1] = data[11];
        subnet_mask[0] = data[12];
        subnet_mask[1] = data[13];
        subnet_mask[2] = data[14];
        subnet_mask[3] = data[15];
        gateway[0] = data[16];
        gateway[1] = data[17];
        gateway[2] = data[18];
        gateway[3] = data[19];
        upp_com_ip[0] = data[20];
        upp_com_ip[1] = data[21];
        upp_com_ip[2] = data[22];
        upp_com_ip[3] = data[23];
        report = data[26];

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
