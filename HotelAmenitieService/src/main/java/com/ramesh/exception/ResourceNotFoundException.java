package com.ramesh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ramesh.Yaleru
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public ResourceNotFoundException() {
	        super();
	    }
	    public ResourceNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public ResourceNotFoundException(String message) {
	        super(message);
	    }
	    public ResourceNotFoundException(Throwable cause) {
	        super(cause);
	    }
}
