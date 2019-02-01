package com.ramesh.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ramesh.domain.Hotel;

/**
 * @author Ramesh.Yaleru
 *
 */
@Component
public class SpringBatchProcessor implements ItemProcessor<Hotel, Hotel>{
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchProcessor.class);
	@Override
	public Hotel process(Hotel hotel) throws Exception {
		LOGGER.info("#### start :: SpringBatchProcessor :: process  ");
		return hotel;
	}

}
