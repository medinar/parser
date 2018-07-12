package com.ef.service.batch.configuration;

import com.ef.config.AppConfig;
import com.ef.domain.AccessLog;
import com.ef.service.batch.item.processor.AccessLogItemProcessor;
import com.ef.service.batch.listener.JobCompletionNotificationListener;
import com.ef.service.batch.mapper.AccessLogFieldSetMapper;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author Rommel Medina
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private AppConfig appConfig;
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
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
                .delimiter(appConfig.getDelimeter())
                .names(appConfig
                        .getAccessLogColumnNames()
                        .toArray(new String[appConfig
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
                .sql(appConfig.getInsertAccessLogQuery())
                .dataSource(dataSource)
                .build();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importAccessLogJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get("IMPORT_ACCESS_LOG_JOB")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }
    
    @Bean
    public Step step(JdbcBatchItemWriter<AccessLog> writer) {
        return stepBuilderFactory.get("step")
                .<AccessLog, AccessLog>chunk(appConfig.getChunkSize())
                .reader(reader())
                .processor(processor)
                .writer(writer)
                // .skipLimit(10) //default is set to 0
                // .skip(MySQLIntegrityConstraintViolationException.class)
                .build();
    }
    // end::jobstep[]

}
