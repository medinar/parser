package com.ef.dao;

import com.ef.config.AppConfig;
import com.ef.domain.Blacklist;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rommel Medina
 */
@Repository
public class BlacklistDaoImpl extends NamedParameterJdbcDaoSupport implements BlacklistDao {

    @Autowired
    AppConfig config;

    @Autowired
    private DataSource dataSource;

    /**
     * Injects the data source to NamedParameterJdbcDaoSupport.
     */
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void save(Blacklist blacklist) throws SQLException {
        getJdbcTemplate().update(config.getInsertBlacklistQuery(), new Object[]{
            blacklist.getIpAddress(),
            blacklist.getReason()
        });
    }

}
