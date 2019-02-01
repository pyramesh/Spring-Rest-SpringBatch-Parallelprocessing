package com.ramesh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Ramesh.Yaleru
 *
 */
@SpringBootApplication (scanBasePackages = "com.ramesh.*")
@EnableJpaRepositories("com.ramesh.*")
@EntityScan( basePackages = {"com.ramesh.*"} )
public class HotelAmenitieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelAmenitieServiceApplication.class, args);
	}

}

