package com.ef.domain;

/**
 *
 *
 * @author Rommel Medina
 */
public class BlockedIp {

    private String ipAddress;
    private String reason;

    public BlockedIp() {
    }

    public BlockedIp(String ipAddress, String reason) {
        this.ipAddress = ipAddress;
        this.reason = reason;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "BlockedIp{" + "ipAddress=" + ipAddress + ", reason=" + reason + '}';
    }

}
