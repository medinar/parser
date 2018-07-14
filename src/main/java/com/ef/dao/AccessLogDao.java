package com.ef.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface AccessLogDao {

    static final String PARAM_START_DATE = "start_date";
    static final String PARAM_LOG_DURATION = "log_duration";
    static final String PARAM_THRESHOLD = "threshold";

    public abstract List<String> findIpAddresses(
            String startDateString,
            String duration,
            int threshold) throws SQLException;

}
