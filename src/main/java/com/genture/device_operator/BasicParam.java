package com.genture.device_operator;

/**
 * Created by zhuj@genture.com on 2017/7/9.
 */
public class BasicParam {
    private String screen_number;
    private String ip;
    private String port;
    private String subnet_mask;
    private String gateway;
    private String upp_com_ip;
    private String report;

    {
        screen_number = "1";
        ip = "192.168.0.2";
        port = "5000";
        subnet_mask = "255.255.255.0";
        gateway = "192.168.0.1";
        upp_com_ip = "192.168.0.3";
        report = "0";
    }

    public String getScreen_number() {
        return screen_number;
    }

    public void setScreen_number(String screen_number) {
        this.screen_number = screen_number;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSubnet_mask() {
        return subnet_mask;
    }

    public void setSubnet_mask(String subnet_mask) {
        this.subnet_mask = subnet_mask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getUpp_com_ip() {
        return upp_com_ip;
    }

    public void setUpp_com_ip(String upp_com_ip) {
        this.upp_com_ip = upp_com_ip;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
