package com.wallethub.parser.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rommel Medina
 */
@Configuration
@ConfigurationProperties("app")
public class AppConfig {

    private int chunkSize; // spring batch commit-interval
    private int maxThread;
    private String delimeter; // spring batch delimeter
    private String insertAccessLogQuery;
    private String findBlockedIpAddressesQuery;
    private String insertBlockedIpQuery;
    private List<String> accessLogColumnNames;
    private final Map<String, Long> duration = new HashMap<>();
    private final Map<String, Integer> threshold = new HashMap<>();
    private String datePattern;

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public int getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(int maxThread) {
        this.maxThread = maxThread;
    }

    public String getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }

    public String getInsertAccessLogQuery() {
        return insertAccessLogQuery;
    }

    public void setInsertAccessLogQuery(String insertAccessLogQuery) {
        this.insertAccessLogQuery = insertAccessLogQuery;
    }

    public List<String> getAccessLogColumnNames() {
        return accessLogColumnNames;
    }

    public void setAccessLogColumnNames(List<String> accessLogColumnNames) {
        this.accessLogColumnNames = accessLogColumnNames;
    }

    public Map<String, Integer> getThreshold() {
        return threshold;
    }

    public Map<String, Long> getDuration() {
        return duration;
    }

    public String getFindBlockedIpAddressesQuery() {
        return findBlockedIpAddressesQuery;
    }

    public void setFindBlockedIpAddressesQuery(String findBlockedIpAddressesQuery) {
        this.findBlockedIpAddressesQuery = findBlockedIpAddressesQuery;
    }

    public String getInsertBlockedIpQuery() {
        return insertBlockedIpQuery;
    }

    public void setInsertBlockedIpQuery(String insertBlockedIpQuery) {
        this.insertBlockedIpQuery = insertBlockedIpQuery;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

}
