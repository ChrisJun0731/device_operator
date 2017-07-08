package com.genture.device_operator;

/**
 * Created by zhuj@genture.com on 2017/6/8.
 */
public class Device {
    private String ip;
    private int port;

    public Device(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
