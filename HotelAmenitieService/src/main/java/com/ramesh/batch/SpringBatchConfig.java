package com.ramesh.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.ramesh.domain.Hotel;

/**
 * @author Ramesh.Yaleru
 *
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchConfig.class);

	@Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;
	
    @Autowired
    ItemReader<Hotel> itemReader;
    
    @Autowired
    ItemProcessor<Hotel, Hotel> itemProcessor;
    
    @Autowired
    ItemWriter<Hotel> itemWriter;
    
	@Bean
    public Job parallelStepsJob() {

	    final Flow masterFlow = new FlowBuilder<Flow>("masterFlow").start(processApplications("STEP-1")).end();

        Flow slaveFlow1 = new FlowBuilder<Flow>("slaveFlow1").start(processApplications("STEP-2")).build();
        Flow slaveFlow2 = new FlowBuilder<Flow>("slaveFlow2").start(processApplications("STEP-3")).build();
        Flow slaveFlow3 = new FlowBuilder<Flow>("slaveFlow3").start(processApplications("STEP-4")).build();

        Flow slaveFlow = new FlowBuilder<Flow>("slaveFlow")
                .split(new SimpleAsyncTaskExecutor()).add(slaveFlow1, slaveFlow2, slaveFlow3).build();

        return (jobBuilderFactory.get("parallelFlowJob")
                .incrementer(new RunIdIncrementer())
                .start(masterFlow)
                .split(new SimpleAsyncTaskExecutor())
                .add(slaveFlow)
                .build()).build();

    }
	
	private Step processApplications(String name) {
		LOGGER.info("step "+name);
		return stepBuilderFactory.get(name)
                .<Hotel, Hotel>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
	}

	@Bean
    public FlatFileItemReader<Hotel> itemReader(@Value("${input}") Resource resource) {
		LOGGER.info("#### start :: SpringBatchConfig :: itemReader  ");
        FlatFileItemReader<Hotel> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        LOGGER.info("#### end :: SpringBatchConfig :: itemReader  ");
        return flatFileItemReader;
    }

	@Bean
    public LineMapper<Hotel> lineMapper() {
		LOGGER.info("#### start :: SpringBatchConfig :: lineMapper  ");
        DefaultLineMapper<Hotel> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"hotelName", "description", "cityCode"});

        BeanWrapperFieldSetMapper<Hotel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Hotel.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        LOGGER.info("#### end :: SpringBatchConfig :: lineMapper  ");
        return defaultLineMapper;
    }
	
}
