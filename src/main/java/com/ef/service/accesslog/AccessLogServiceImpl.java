package com.ef.service.accesslog;

import com.ef.dao.AccessLogDao;
import com.ef.exception.AccessLogServiceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rommel Medina
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final Logger logger
            = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    JobLauncher asyncJobLauncher;

    @Autowired
    Job parseAndSaveAccessLogJob;

    @Autowired
    AccessLogDao accessLogDao;

    @Override
    public List<String> getBlockedIpAddresses(
            String startDate,
            String endDate,
            int threshold) throws AccessLogServiceException {

        List<String> ipAddresses = new ArrayList<>();
        try {
            logger.info(
                    "getBlockedIpAddresses(startDate={}, endDate={}, threshold={})",
                    startDate, endDate, threshold
            );

            ipAddresses = accessLogDao.findIpAddresses(
                    startDate,
                    endDate,
                    threshold
            );
        }
        catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            throw new AccessLogServiceException(ex);
        }

        return ipAddresses;
    }

    @Override
    public void parseAndSave(String path) throws AccessLogServiceException {
        try {
            JobExecution jobExecution = asyncJobLauncher.run(
                    parseAndSaveAccessLogJob,
                    new JobParametersBuilder()
                            .addString(PATH, path)
                            .addLong(TIME, System.currentTimeMillis())
                            .toJobParameters()
            );

            while (jobExecution.getStatus() != BatchStatus.ABANDONED
                    && jobExecution.getStatus() != BatchStatus.COMPLETED
                    && jobExecution.getStatus() != BatchStatus.FAILED
                    && jobExecution.getStatus() != BatchStatus.STOPPED) {
            }
        }
        catch (JobExecutionAlreadyRunningException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException
                | JobRestartException ex) {

            logger.error(ex.getMessage(), ex);
            throw new AccessLogServiceException(ex);
        }
    }

}
