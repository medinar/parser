package com.ef.service.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rommel Medina
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final JdbcTemplate jdbcTemplate; // TODO: Move this to Repositoy or DAO.

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("!!! JOB FINISHED! Time to verify the results");
//            jdbcTemplate.query(
//                    "SELECT log_date, ip_address, request, status, user_agent FROM access_log",
//                    (rs, row) -> new AccessLog(
//                            rs.getString(1),
//                            rs.getString(2),
//                            rs.getString(3),
//                            rs.getString(4),
//                            rs.getString(5)
//                    )
//            ).forEach(accessLog -> logger.info(
//                    "Found <{}> in the database.",
//                    accessLog
//            ));
            int count = jdbcTemplate
                    .queryForObject("SELECT count(0) FROM access_log", Integer.class);
            logger.info("count -> {}", count);
        }
    }

}
