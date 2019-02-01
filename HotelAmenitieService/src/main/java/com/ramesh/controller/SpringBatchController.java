package com.ramesh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ramesh.Yaleru
 *
 */
@RestController
@RequestMapping("/importfile")
public class SpringBatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@GetMapping
	public BatchStatus importFile() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {

		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);

		LOGGER.info("JobExecution: " + jobExecution.getStatus());

		LOGGER.info("########## Batch is Running ###############");
		while (jobExecution.isRunning()) {
			LOGGER.info("**************************");
		}

		return jobExecution.getStatus();
	}

}