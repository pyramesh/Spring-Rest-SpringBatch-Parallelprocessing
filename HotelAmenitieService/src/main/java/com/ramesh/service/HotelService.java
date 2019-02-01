package com.ramesh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ramesh.domain.Hotel;
import com.ramesh.respository.HotelRepository;

/**
 * @author Ramesh.Yaleru
 *
 */
@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	/**
	 * @param pageable
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Hotel> getAllHotels(Pageable pageable){
		return hotelRepository.findAll(pageable);
	}
	
	/**
	 * @param hotel
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Hotel saveHotel(Hotel hotel) {
		
		return hotelRepository.save(hotel);
	}
	
	/**
	 * @param hotelId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<Object> deleteHotel(Long hotelId) {
		
		return hotelRepository.findById(hotelId).map(post -> {
			hotelRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Hotel ID " + hotelId + " is not found"));
	}
	
	/**
	 * @param hotelId
	 * @param requestHotel
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Hotel updateHotel(Long hotelId, Hotel requestHotel) {
	return hotelRepository.findById(hotelId).map(updatedHotel -> {
		updatedHotel.setHotelName(requestHotel.getHotelName());
		updatedHotel.setDescription(requestHotel.getDescription());
		updatedHotel.setCityCode(requestHotel.getCityCode());
        return hotelRepository.save(updatedHotel);
    }).orElseThrow(() -> new ResourceNotFoundException("hotelId " + hotelId + " is not found"));
	}
}
