package com.ramesh.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramesh.domain.Hotel;
import com.ramesh.exception.ResourceNotFoundException;
import com.ramesh.service.HotelService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Ramesh.Yaleru
 *
 */
@RestController
public class HotelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	private HotelService hotelService;
	
	@RequestMapping("/")
    @ApiIgnore
    public String swaggerHomePage() {
        return "http://localhost:9999/swagger-ui.html";
    }
	
	/**
	 * @param pageable
	 * @return
	 */
	@GetMapping("/hotels/all")
	 @ApiOperation(value = "fetch All Hotel available.")
	public Page<Hotel> getAllHotels(Pageable pageable){
		LOGGER.info("####### HotelController :: start :: getAllHotels #####");
		Page<Hotel> paginatedHotels = hotelService.getAllHotels(pageable);
		if(paginatedHotels.get() == null) {
			throw new ResourceNotFoundException("No records found.");
		}
		LOGGER.info("####### HotelController :: end :: getAllHotels #####");
		return paginatedHotels;
	}
	
	/**
	 * @param hotel
	 * @return
	 */
	@PostMapping("/hotels/create")
	@ApiOperation(value = "create Hotel Entity.")
	public Hotel saveHotel(@RequestBody Hotel hotel) {
		LOGGER.info("####### HotelController :: start :: saveHotel #####");
		Hotel hotelRes =  hotelService.saveHotel(hotel);
		LOGGER.info("####### HotelController :: end :: getAllHotels #####");
		return hotelRes;
	}

	/**
	 * @param hotelId
	 * @return
	 */
	@DeleteMapping("/hotels/{hotelId}")
	@ApiOperation(value = "Delete the Hotel by Hotel ID")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId) {
        
		return hotelService.deleteHotel(hotelId);
    }

	/**
	 * @param hotelId
	 * @param hotel
	 * @return
	 */
	@PutMapping("/hotels/{hotelId}")
	@ApiOperation(value = "Update Hotel By Hotel ID.")
    public Hotel updateHotel(@PathVariable Long hotelId, @Valid @RequestBody Hotel hotel) {
        
		return hotelService.updateHotel(hotelId, hotel);
    }
	
}
