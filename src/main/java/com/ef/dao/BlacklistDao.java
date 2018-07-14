package com.ef.dao;

import com.ef.domain.Blacklist;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlacklistDao {

    static final String PARAM_IP_ADDRESS = "ip_address";
    static final String PARAM_REASON = "reason";

    public abstract int save(Blacklist blacklist) throws SQLException;

    public abstract int[] saveAll(List<Blacklist> blacklistedIps) throws SQLException;

}
