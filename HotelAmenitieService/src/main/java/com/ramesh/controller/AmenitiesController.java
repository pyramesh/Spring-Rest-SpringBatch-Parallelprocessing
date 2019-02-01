package com.ramesh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramesh.domain.Amenity;
import com.ramesh.service.AmenityService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Ramesh.Yaleru
 *
 */
@RestController
public class AmenitiesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AmenitiesController.class);
	@Autowired
	AmenityService amenityService;
	
	/**
	 * @param amenity
	 * @return
	 */
	@PostMapping("/amenity/create")
	@ApiOperation(value = "Update Hotel By Hotel ID.")
	public Amenity saveAmenity(@RequestBody Amenity amenity) {
		LOGGER.info("####### AmenitiesController :: start :: saveAmenity #####");
		return amenityService.saveAmenity(amenity);
	}
	
	/**
	 * @return
	 */
	@GetMapping("/amenity/all")
	@ApiOperation(value = "Fetch all Amenities available.")
	public List<Amenity> getAllAmenities(){
		LOGGER.info("####### AmenitiesController :: start :: getAllAmenities #####");
		return amenityService.getAllAmenities();
	}
	
	
	/**
	 * @param amenityId
	 * @return
	 */
	@DeleteMapping("/amenity/delete/{amenityId}")
	@ApiOperation(value = "Delete Amenity by amenity ID.")
    public ResponseEntity<?> deleteAmenity(@PathVariable Long amenityId) {
		LOGGER.info("####### AmenitiesController :: start :: deleteAmenity #####");
		return amenityService.deleteHotel(amenityId);
    }
	
	/**
	 * @param hotelId
	 * @return
	 */
	@GetMapping("/hotels/{hotelId}/amenties")
	@ApiOperation(value = "Get all Amenities by hotel ID.")
	public List<Amenity> getAmenitiesByHotel(@PathVariable (value = "hotelId") Long hotelId){
		LOGGER.info("####### AmenitiesController :: start :: getAmenitiesByHotel #####");
		List<Amenity> list =  amenityService.getAmenitiesByHotel(hotelId);
		if(list == null) {
			new ResourceNotFoundException("Amenities not found for the hotel Id " + hotelId );
		}
		LOGGER.info("####### AmenitiesController :: end :: getAmenitiesByHotel #####");
		return list;
	} 
	
	/**
	 * @param roomId
	 * @return
	 */
	@GetMapping("/rooms/{roomId}/amenties")
	@ApiOperation(value = "Get all Amenities by room ID.")
	public List<Amenity> getAmenitiesByRoom(@PathVariable (value = "roomId") Long roomId){
		LOGGER.info("####### AmenitiesController :: start :: getAmenitiesByRoom #####");
		List<Amenity> list =  amenityService.getAmenitiesByRoom(roomId);
		if(list == null) {
			new ResourceNotFoundException("Amenities not found for the Room Id " + roomId );
		}
		LOGGER.info("####### AmenitiesController :: end :: getAmenitiesByRoom #####");
		return list;
	}
}
