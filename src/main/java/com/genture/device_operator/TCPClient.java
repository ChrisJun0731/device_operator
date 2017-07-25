package com.genture.device_operator;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhuj@genture.com on 2017/6/7.
 */
class TCPClient {
    private static Logger logger = Logger.getLogger(TCPClient.class);
    private Socket socket;

    public TCPClient(Device device){
        try{
            this.socket = new Socket(device.getIP(), device.getPort());
        }
        catch(Exception e){
            logger.error("连接超时，请检测网络是否连通，以及设备ip和端口号是否设置正确！");
        }
    }

    /**
     * 向服务器端发送帧数据
     * @param dataFrame
     */
    public void send(byte[] dataFrame){
        try{
            OutputStream os = this.socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(dataFrame);
            bos.flush();
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 从服务器端接受数据，并自动判断接受数据的大小
     * @return
     */
    public byte[] receive(){
        byte[] result = null;
        try{
            InputStream is = this.socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            //为了防止服务器端还没向流中发送数据，让线程阻塞,先读一个字节。
            byte first_byte = (byte)bis.read();
            int available = bis.available();
            result = new byte[available+1];
            bis.read(result, 1, available);
            result[0] = first_byte;
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }

    public void close(){
        try{
            this.socket.close();
            logger.info("成功与设备断开连接!");
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }
}
