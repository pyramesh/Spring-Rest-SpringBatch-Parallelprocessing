package com.ramesh.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
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
import com.ramesh.domain.HotelAmenity;
import com.ramesh.domain.Room;
import com.ramesh.service.HotelService;

/**
 * @author Ramesh.Yaleru
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelAmenitieServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelControllerTest {

	@Autowired
	HotelService hotelService;
	
	@LocalServerPort
    private int port;

    
    TestRestTemplate restTemplate = new TestRestTemplate();
    
	@Test
	public void testsaveHotel_2() {
		
		final Hotel hotel = new Hotel("Burj Al Arab", "Burj Al Arab Dubai", "UAE");
		
		
		Set<Room> rooms = new HashSet<Room>();
		
		/*List<Amenity> amenities = new ArrayList<Amenity>();
		 * Amenity amenity = new Amenity("Parking", "Free Paking");
		Amenity amenity1 = new Amenity("Bar", "Bar");
		Amenity amenity2 = new Amenity("Fitnes", "Fitness Center");
		amenities.add(amenity);
		amenities.add(amenity1);
		amenities.add(amenity2);*/
		
		
		Room room = new Room("ROOM-101");
		Room room1 = new Room("ROOM-102");
		Room room2 = new Room("ROOM-103");
		rooms.add(room);
		rooms.add(room1);
		rooms.add(room2);
		
		//hotel.setAmenities(amenities);
		hotel.setRooms(rooms);
		
        HttpEntity<Hotel> entity = new HttpEntity<>(hotel, null);
        
        //ResponseEntity<Hotel> response = restTemplate.postForEntity("/hotels/create", entity, Hotel.class);
        
        ResponseEntity<Hotel> response = restTemplate.exchange(createURLWithPort("/hotels/create", port),
                HttpMethod.POST,
                entity,
                Hotel.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

	}
	
	
	@Test
	public void testsaveHotel_1() {
		
		final Hotel hotel = new Hotel("City Max", "City Max", "UAE");
		
		Set<Room> rooms = new HashSet<Room>();
		Room room = new Room("ROOM-101");
		Room room1 = new Room("ROOM-102");
		Room room2 = new Room("ROOM-103");
		rooms.add(room);
		rooms.add(room1);
		rooms.add(room2);
		
		hotel.setRooms(rooms);
		HttpEntity<Hotel> entity = new HttpEntity<>(hotel, null);
        
		ResponseEntity<Hotel> response = restTemplate.exchange(createURLWithPort("/hotels/create", port),
                HttpMethod.POST,
                entity,
                Hotel.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

	}
	
	@Test
	public void testsaveHotel_WithAllObject() {
		
		Set<Room> rooms = new HashSet<Room>();
		Room room = new Room("ROOM-101");
		Room room1 = new Room("ROOM-102");
		Room room2 = new Room("ROOM-103");
		rooms.add(room);
		rooms.add(room1);
		rooms.add(room2);
		
		Amenity am = new Amenity("TV", "Television");
		Amenity am1 = new Amenity("WIFI", "Internet");
		
		HotelAmenity hAmenity = new HotelAmenity(am, false, 250.50);
		HotelAmenity hAmenity1 = new HotelAmenity(am1, true, 300.50);
		Set<HotelAmenity>hotelAmenities= new HashSet<>();
		hotelAmenities.add(hAmenity);
		hotelAmenities.add(hAmenity1);
		
		final Hotel hotel = new Hotel("Parkside Hotel", "Parkside Hotel", "UAE", hotelAmenities, rooms);
		
		Hotel h = hotelService.saveHotel(hotel);
		System.out.println("H :: ID "+h.getId());
		
		
	/*	HttpEntity<Hotel> entity = new HttpEntity<>(hotel, null);
        
		ResponseEntity<Hotel> response = restTemplate.exchange(createURLWithPort("/hotels/create", port),
                HttpMethod.POST,
                entity,
                Hotel.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));*/

	}

	
	@Test
	public void testsaveHotel_WithAllObject_1() {
		
		Set<Room> rooms = new HashSet<Room>();
		Room room = new Room("ROOM-101");
		Room room1 = new Room("ROOM-201");
		Room room2 = new Room("ROOM-301");
		rooms.add(room);
		rooms.add(room1);
		rooms.add(room2);
		
		Amenity am = new Amenity("GYM", "Fitnet Center");
		Amenity am1 = new Amenity("SWIM", "Swimming Pool");
		
		HotelAmenity hAmenity = new HotelAmenity(am, false, 250.50);
		HotelAmenity hAmenity1 = new HotelAmenity(am1, true, 300.50);
		Set<HotelAmenity>hotelAmenities= new HashSet<>();
		hotelAmenities.add(hAmenity);
		hotelAmenities.add(hAmenity1);
		
		final Hotel hotel = new Hotel("Burj Al Arab", "Burj Al Arab", "UAE", hotelAmenities, rooms);
		
		Hotel h = hotelService.saveHotel(hotel);
		System.out.println("H :: ID "+h.getId());
		
		
	/*	HttpEntity<Hotel> entity = new HttpEntity<>(hotel, null);
        
		ResponseEntity<Hotel> response = restTemplate.exchange(createURLWithPort("/hotels/create", port),
                HttpMethod.POST,
                entity,
                Hotel.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));*/

	}
	
	
	
	public static String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }
}
