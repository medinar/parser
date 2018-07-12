package com.ef.config;

import java.util.List;
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
    private String delimeter; // spring batch delimeter
    private String insertAccessLogQuery;
    private List<String> accessLogColumnNames;

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
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

}
