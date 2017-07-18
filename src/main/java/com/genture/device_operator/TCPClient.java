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
            logger.error(e.getMessage());
        }
    }

    /**
     * ��������˷���֡����
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
     * �ӷ������˽������ݣ����Զ��жϽ������ݵĴ�С
     * @return
     */
    public byte[] receive(){
        byte[] result = null;
        try{
            InputStream is = this.socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            //Ϊ�˷�ֹ�������˻�û�����з������ݣ����߳�����,�ȶ�һ���ֽڡ�
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
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }
}
