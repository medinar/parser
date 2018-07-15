package com.ef.service.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rommel Medina
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("JOB {} {}",
                jobExecution.getJobId(),
                jobExecution.getExitStatus().getExitCode()
        );
    }

}
