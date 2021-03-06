package com.wallethub.parser.dao;

import com.wallethub.parser.config.AppConfig;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rommel Medina
 */
@Repository
public class AccessLogDaoImpl extends NamedParameterJdbcDaoSupport implements AccessLogDao {

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
    public List<String> findIpAddresses(
            String startDate,
            String endDate,
            int threshold) throws SQLException {

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(PARAM_START_DATE, startDate)
                .addValue(PARAM_END_DATE, endDate)
                .addValue(PARAM_THRESHOLD, threshold);

        return getNamedParameterJdbcTemplate()
                .queryForList(
                        config.getFindBlockedIpAddressesQuery(),
                        sqlParameterSource,
                        String.class
                );
    }

}
