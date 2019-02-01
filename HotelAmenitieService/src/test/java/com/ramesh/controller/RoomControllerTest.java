package com.ramesh.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.ramesh.service.RoomService;

/**
 * @author Ramesh.Yaleru
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelAmenitieServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RoomControllerTest {

	@LocalServerPort
    private int port;
	
	@Autowired
	RoomService roomService;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void testgetAllRoomsByHotelId() {
		
		List<Room> rooms = roomService.getAllRoomsByHotelId(1l);
		System.out.println("H :: ID "+rooms.size());
		assertThat(rooms.size(), equalTo(3));
		
		/*Hotel hotel = new Hotel();
		 HttpEntity<Hotel> entity = new HttpEntity<>(hotel, null);
		ResponseEntity<Room> response = restTemplate.exchange(createURLWithPort("/hotels/1l/rooms", port),
                HttpMethod.GET,
                entity,
                Room.class);
		System.out.println("response Code #############"+response.getStatusCode());	
		assertThat(response.getStatusCode(), is(HttpStatus.OK));*/
		
		
		}
	
	@Test
	public void testSaveRoom() {
		
		Room room = new Room("ROOM-4");
		Room roomRes = roomService.saveRoom(1l, room);
		System.out.println("H :: ID "+roomRes.getId());
		}
	
	public static String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }
	
}
