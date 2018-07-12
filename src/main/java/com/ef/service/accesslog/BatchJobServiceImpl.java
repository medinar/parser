package com.ef.service.accesslog;

import com.ef.exception.BatchJobServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
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
public class BatchJobServiceImpl implements BatchJobService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job importAccessLogJob;

    @Override
    public void launch(String path) throws BatchJobServiceException {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("path", path)
                    // This is to make sure that each job run is considered a new job instance.
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(importAccessLogJob, jobParameters);
        }
        catch (JobExecutionAlreadyRunningException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException
                | JobRestartException ex) {

            logger.error(ex.getMessage(), ex);
            throw new BatchJobServiceException(ex);
        }
    }

}
