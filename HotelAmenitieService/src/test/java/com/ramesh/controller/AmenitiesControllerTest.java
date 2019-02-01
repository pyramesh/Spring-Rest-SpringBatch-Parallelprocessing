package com.ramesh.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ramesh.app.HotelAmenitieServiceApplication;
import com.ramesh.domain.Amenity;
import com.ramesh.domain.Hotel;
import com.ramesh.domain.Room;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelAmenitieServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AmenitiesControllerTest {

	
	@LocalServerPort
    private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
    
    
	@Test
	public void testsaveAmenity_1() {
		
		final Amenity amenity = new Amenity("PARKING", "Free Parking");
		
	    HttpEntity<Amenity> entity = new HttpEntity<>(amenity, null);
    
	    ResponseEntity<Amenity> response = restTemplate.exchange(createURLWithPort("/amenity/create", port),
                HttpMethod.POST,
                entity,
                Amenity.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}
	
	@Test
	public void testsaveAmenity_2() {
		
		final Amenity amenity = new Amenity("GYM", "Fitness Center");
		
	    HttpEntity<Amenity> entity = new HttpEntity<>(amenity, null);
    
	    ResponseEntity<Amenity> response = restTemplate.exchange(createURLWithPort("/amenity/create", port),
                HttpMethod.POST,
                entity,
                Amenity.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

	}
	
	@Test
	public void testsaveAmenity_3() {
		
		final Amenity amenity = new Amenity("BAR", "Bar");
		
	    HttpEntity<Amenity> entity = new HttpEntity<>(amenity, null);
    
	    ResponseEntity<Amenity> response = restTemplate.exchange(createURLWithPort("/amenity/create", port),
                HttpMethod.POST,
                entity,
                Amenity.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

	}
	
	@Test
	public void testsaveAmenity_4() {
		
		final Amenity amenity = new Amenity("TV", "TV");
		
	    HttpEntity<Amenity> entity = new HttpEntity<>(amenity, null);
    
	    ResponseEntity<Amenity> response = restTemplate.exchange(createURLWithPort("/amenity/create", port),
                HttpMethod.POST,
                entity,
                Amenity.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}
	
	@Test
	public void testsaveAmenity_5() {
		
		final Amenity amenity = new Amenity("fridge", "Refridgerator");
		
	    HttpEntity<Amenity> entity = new HttpEntity<>(amenity, null);
    
	    ResponseEntity<Amenity> response = restTemplate.exchange(createURLWithPort("/amenity/create", port),
                HttpMethod.POST,
                entity,
                Amenity.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}
	
	@Test
	public void testgetAllAmenities() {
		
		final Amenity amenity = new Amenity();
		
   
	    ResponseEntity<List> response = restTemplate.exchange(createURLWithPort("/amenity/all", port),
                HttpMethod.GET,
                null,
                List.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}
	

	
	public static String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }
	
}
