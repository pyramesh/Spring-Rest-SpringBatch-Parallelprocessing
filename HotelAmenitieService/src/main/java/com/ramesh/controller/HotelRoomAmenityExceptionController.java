package com.ramesh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ramesh.exception.ResourceNotFoundException;

/**
 * @author Ramesh.Yaleru
 *
 */
@ControllerAdvice
public class HotelRoomAmenityExceptionController {

	 @ExceptionHandler(value = ResourceNotFoundException.class)
	   public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
	      return new ResponseEntity<>("Resource not Found ", HttpStatus.NOT_FOUND);
	   }
}
