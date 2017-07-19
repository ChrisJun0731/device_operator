package com.genture.device_operator;

/**
 * Created by zhuj@genture.com on 2017/6/8.
 */
public class Device {
    private String ip;
    private int port;

    /**
     * Device的构造函数
     * @param ip 设备的ip地址
     * @param port 设备的端口号
     */
    public Device(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    /**
     * 获得设备ip
     * @return ip
     */
    public String getIP() {
        return ip;
    }

    /**
     * 设置设备ip
     * @param ip ip
     */
    public void setIP(String ip) {
        this.ip = ip;
    }

    /**
     * 获得设备端口号
     * @return 端口号
     */
    public int getPort() {
        return port;
    }

    /**
     * 设置设备端口号
     * @param port 端口号
     */
    public void setPort(int port) {
        this.port = port;
    }
}
