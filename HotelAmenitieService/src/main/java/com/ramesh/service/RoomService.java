package com.ramesh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ramesh.domain.Room;
import com.ramesh.respository.HotelRepository;
import com.ramesh.respository.RoomRepository;

/**
 * @author Ramesh.Yaleru
 *
 */
@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	HotelRepository hotelRepository;
	
	/**
	 * @param hotelId
	 * @param pageable
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Room> getAllRoomsByHotelId(Long hotelId) {
		List<Room> rooms = roomRepository.findByHotelId(hotelId);
		return rooms;
	}

	/**
	 * @param hotelId
	 * @param room
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Room saveRoom(Long hotelId, Room room) {
		return hotelRepository.findById(hotelId).map(hotel -> {
			room.setHotel(hotel);
			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("Hotel ID " + hotelId + "is not found"));
	}

	/**
	 * @param hotelId
	 * @param roomId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<?> deleteRoom(Long hotelId, Long roomId) {
	 return roomRepository.findByIdAndHotelId(roomId, hotelId).map(room -> {
		 roomRepository.delete(room);
         return ResponseEntity.ok().build();
     }).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId + " and HotelId " + hotelId));
	}
}
