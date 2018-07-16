package com.wallethub.parser.service.batch.configuration;

import com.wallethub.parser.config.AppConfig;
import com.wallethub.parser.domain.AccessLog;
import com.wallethub.parser.service.batch.item.processor.AccessLogItemProcessor;
import com.wallethub.parser.service.batch.listener.JobCompletionNotificationListener;
import com.wallethub.parser.service.batch.mapper.AccessLogFieldSetMapper;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author Rommel Medina
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private AppConfig config;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // -------------------------------------------------------------------------
    @Bean
    public PlatformTransactionManager resourcelessTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        return new MapJobRepositoryFactoryBean(resourcelessTransactionManager())
                .getObject();
    }

    @Bean
    public JobLauncher asyncJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.setTaskExecutor(taskExecutor());
        return jobLauncher;
    }

    // -------------------------------------------------------------------------
    // Optimization:
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(config.getMaxThread());
        return taskExecutor;
    }

    // -------------------------------------------------------------------------
    @Autowired
    private AccessLogItemProcessor processor;

    @Autowired
    private AccessLogFieldSetMapper accessLogFieldSetMapper;

    @Bean
    @StepScope
    public Resource resource(@Value("#{jobParameters[path]}") String path) {
        return new FileSystemResource(path);
    }

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<AccessLog> reader() {
        return new FlatFileItemReaderBuilder<AccessLog>()
                .name("accessLogItemReader")
                .resource(resource("")) // Argument to resource injected from jobParameter.
                .delimited()
                .delimiter(config.getDelimeter())
                .names(config
                        .getAccessLogColumnNames()
                        .toArray(new String[config
                                .getAccessLogColumnNames().size()]
                        )
                )
                .fieldSetMapper(accessLogFieldSetMapper)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<AccessLog> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AccessLog>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(config.getInsertAccessLogQuery())
                .dataSource(dataSource)
                .build();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job parseAndSaveAccessLogJob(
            JobCompletionNotificationListener listener,
            Step step) {
        return jobBuilderFactory.get("parse-and-save-access-log-job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<AccessLog> writer) {
        return stepBuilderFactory.get("step")
                .<AccessLog, AccessLog>chunk(config.getChunkSize())
                .reader(reader())
                .processor(processor)
                .writer(writer)
                .build();
    }
    // end::jobstep[]

}
