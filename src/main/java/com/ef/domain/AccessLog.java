package com.ef.domain;

/**
 *
 * @author Rommel Medina
 */
public class AccessLog {
    private String logDate;
    private String ipAddress; // IP Address
    private String request; // Request Method
    private String status; // Http Status Code
    private String userAgent;

    public AccessLog() {
    }

    public AccessLog(String logDate, String ipAddress, String request, String status, String userAgent) {
        this.logDate = logDate;
        this.ipAddress = ipAddress;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "AccessLog{" + "logDate=" + logDate + ", ipAddress=" + ipAddress + ", request=" + request + ", status=" + status + ", userAgent=" + userAgent + '}';
    }

}
