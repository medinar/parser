package com.ef.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface AccessLogDao {

    public abstract List<String> findIpAddresses(
            String startDateString,
            String duration,
            int threshold) throws SQLException;

}
