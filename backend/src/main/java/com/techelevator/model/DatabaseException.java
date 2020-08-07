package com.techelevator.model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database Error.")
public class DatabaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8824651913675821551L;

}
