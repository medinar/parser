package com.wallethub.parser.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface AccessLogDao {

    static final String PARAM_START_DATE = "start_date";
    static final String PARAM_END_DATE = "end_date";
    static final String PARAM_THRESHOLD = "threshold";

    public abstract List<String> findIpAddresses(
            String startDate,
            String endDate,
            int threshold) throws SQLException;

}
