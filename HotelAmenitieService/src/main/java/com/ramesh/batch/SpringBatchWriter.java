package com.ramesh.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ramesh.domain.Hotel;
import com.ramesh.respository.HotelRepository;

/**
 * @author Ramesh.Yaleru
 *
 */
@Component
public class SpringBatchWriter implements ItemWriter<Hotel>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchWriter.class);
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public void write(List<? extends Hotel> hotels) throws Exception {
		LOGGER.info("#### start :: SpringBatchWriter :: write #### ");
		hotelRepository.saveAll(hotels);
		LOGGER.info("#### end :: SpringBatchWriter :: write  ####");
	}

	
}
