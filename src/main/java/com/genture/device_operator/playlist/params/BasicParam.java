package com.genture.device_operator.playlist.params;

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

    /**
     * 获得屏体编号
     * @return 屏体编号
     */
    public String getScreen_number() {
        return screen_number;
    }

    /**
     * 设置屏体编号
     * @param screen_number 屏体编号
     */
    public void setScreen_number(String screen_number) {
        this.screen_number = screen_number;
    }

    /**
     * 获得ip
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获得端口号
     * @return 端口号
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置端口号
     * @param port 端口号
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 获得子网掩码
     * @return 子网掩码
     */
    public String getSubnet_mask() {
        return subnet_mask;
    }

    /**
     * 设置子网掩码
     * @param subnet_mask 子网掩码
     */
    public void setSubnet_mask(String subnet_mask) {
        this.subnet_mask = subnet_mask;
    }

    /**
     * 设置网关
     * @return 网关
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * 获得网关
     * @param gateway 网关
     */
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    /**
     * 获得上位机的ip
     * @return 上位机ip
     */
    public String getUpp_com_ip() {
        return upp_com_ip;
    }

    /**
     * 设置上位机ip
     * @param upp_com_ip 上位机ip
     */
    public void setUpp_com_ip(String upp_com_ip) {
        this.upp_com_ip = upp_com_ip;
    }

    /**
     * 获得是否上报
     * @return 是否上报
     */
    public String getReport() {
        return report;
    }

    /**
     * 设置是否上报
     * @param report 是否上报
     */
    public void setReport(String report) {
        this.report = report;
    }
}
