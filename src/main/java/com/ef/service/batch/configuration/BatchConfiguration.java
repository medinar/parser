package com.ef.service.batch.configuration;

import com.ef.domain.AccessLog;
import com.ef.service.batch.item.AccessLogItemProcessor;
import com.ef.service.batch.listener.JobCompletionNotificationListener;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author Rommel Medina
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<AccessLog> reader() {
        return new FlatFileItemReaderBuilder<AccessLog>()
                .name("accessLogItemReader")
                .resource(new ClassPathResource("access.log")) // TODO: Move the input file outside the application.
                .delimited()
                .delimiter("|")
                .names(new String[]{"logDate", "ipAddress", "request", "status", "userAgent"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<AccessLog>() {
                    {
                        setTargetType(AccessLog.class);
                    }

                })
                .build();
    }

    @Bean
    public AccessLogItemProcessor processor() {
        return new AccessLogItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<AccessLog> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AccessLog>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO access_log (log_date, ip_address, request, status, user_agent) VALUES (:logDate, :ipAddress, :request, :status, :userAgent)")
                .dataSource(dataSource)
                .build();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importAccessLogJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get("importAccessLogJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<AccessLog> writer) {
        return stepBuilderFactory.get("step")
                .<AccessLog, AccessLog>chunk(20000)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
    // end::jobstep[]
}
