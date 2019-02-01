package com.ramesh.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramesh.domain.Room;
import com.ramesh.service.RoomService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Ramesh.Yaleru
 *
 */
@RestController
public class RoomController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);
	@Autowired
	RoomService roomService;
	
	@GetMapping("/hotels/{hotelId}/rooms")
	 @ApiOperation(value = "fetch All Rooms by hotel ID.")
	public List<Room> getAllRoomsByHotelId(@PathVariable (value = "hotelId") Long hotelId){
		LOGGER.info("####### RoomController :: start :: getAllRoomsByHotelId #####");
		return roomService.getAllRoomsByHotelId(hotelId);
	}
	
	@PostMapping("/hotels/{hotelId}/rooms")
	@ApiOperation(value = "save Room for the provided hotel.")
    public Room saveRoom(@PathVariable (value = "hotelId") Long hotelId,@Valid @RequestBody Room room) {
		LOGGER.info("####### RoomController :: start :: saveRoom #####");
		return roomService.saveRoom(hotelId, room);
    }
	
	@DeleteMapping("/hotels/{hotelId}/rooms/{roomId}")
	@ApiOperation(value = "delete Room under the provided hotel.")
    public ResponseEntity<?> deleteRoom(@PathVariable (value = "hotelId") Long hotelId,
                              @PathVariable (value = "roomId") Long roomId) {
		LOGGER.info("####### RoomController :: start :: deleteRoom #####");
		return roomService.deleteRoom(hotelId, roomId);
    }
	
}
