package com.ramesh.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ramesh.domain.Amenity;
import com.ramesh.domain.HotelAmenity;
import com.ramesh.domain.RoomAmenity;
import com.ramesh.respository.AmenityRepository;
import com.ramesh.respository.HotelAmenityRepository;
import com.ramesh.respository.HotelRepository;
import com.ramesh.respository.RoomAmenityRepository;

/**
 * @author Ramesh.Yaleru
 *
 */
@Service
public class AmenityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AmenityService.class);
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	AmenityRepository amenityRepository;
	
	@Autowired
	HotelAmenityRepository hotelAmenityRepository;
	
	@Autowired
	RoomAmenityRepository roomAmenityRepository;
	
	
	
	/**
	 * @param amenity
	 * @return
	 */
	public Amenity saveAmenity(Amenity amenity) {
		LOGGER.info("####### AmenityService :: start :: saveAmenity #####"+amenity.toString());
		return amenityRepository.save(amenity);
	}
	
	
	/**
	 * @return
	 */
	public List<Amenity> getAllAmenities() {
		LOGGER.info("####### AmenityService :: start :: getAllAmenities #####");
		return amenityRepository.findAll();
	}




	/**
	 * @param amenityId
	 * @return
	 */
	public ResponseEntity<?> deleteHotel(Long amenityId) {
		LOGGER.info("####### AmenityService :: start :: deleteHotel : amenityId #####"+amenityId);
		return amenityRepository.findById(amenityId).map(amenity -> {
			amenityRepository.delete(amenity);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Hotel ID " + amenityId + " is not found"));
	}
	
	
	
	/**
	 * @param hotelId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Amenity> getAmenitiesByHotel(Long hotelId) {
		LOGGER.info("####### AmenityService :: start :: getAmenitiesByHotel : hotelId #####"+hotelId);
		List<HotelAmenity> hotelAmenities = hotelAmenityRepository.findByHotelId(hotelId);
		List<Long> hotelAmenityIds = new ArrayList<>();
		for(HotelAmenity hAmenity : hotelAmenities) {
			hotelAmenityIds.add(hAmenity.getAmenity().getId());
		}
		
		return amenityRepository.findAllById(hotelAmenityIds);
		
	}

	/**
	 * @param roomId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Amenity> getAmenitiesByRoom(Long roomId) {
		LOGGER.info("####### AmenityService :: start :: getAmenitiesByRoom : roomId #####"+roomId);
		List<RoomAmenity> roomAmenities = roomAmenityRepository.findByRoomId(roomId);
		List<Long> roomAmenityIds = new ArrayList<>();
		for(RoomAmenity rAmenity : roomAmenities) {
			roomAmenityIds.add(rAmenity.getAmenity().getId());
		}
		return amenityRepository.findAllById(roomAmenityIds);
	}




	

	

}
