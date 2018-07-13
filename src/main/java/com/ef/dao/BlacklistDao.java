package com.ef.dao;

import com.ef.domain.Blacklist;
import java.sql.SQLException;

/**
 *
 * @author Rommel D. Medina
 */
public interface BlacklistDao {

    public abstract void save(Blacklist blacklist) throws SQLException;

}
