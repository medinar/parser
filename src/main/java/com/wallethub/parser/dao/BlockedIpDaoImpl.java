package com.wallethub.parser.dao;

import com.wallethub.parser.config.AppConfig;
import com.wallethub.parser.domain.BlockedIp;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rommel Medina
 */
@Repository
public class BlockedIpDaoImpl extends NamedParameterJdbcDaoSupport implements BlockedIpDao {

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
    public int save(BlockedIp blockedIp) throws SQLException {
        return getNamedParameterJdbcTemplate().update(
                config.getInsertBlockedIpQuery(),
                new BeanPropertySqlParameterSource(blockedIp)
        );
    }

    // TODO: Add unique index for ip and reason.
    @Override
    public int[] saveAll(List<BlockedIp> blockedIps) throws SQLException {
        return getNamedParameterJdbcTemplate().batchUpdate(
                config.getInsertBlockedIpQuery(),
                SqlParameterSourceUtils.createBatch(blockedIps)
        );
    }

}
