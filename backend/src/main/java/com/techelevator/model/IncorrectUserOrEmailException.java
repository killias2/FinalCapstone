package com.techelevator.model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "User and Email Do Not Match.")
public class IncorrectUserOrEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5395135654808102743L;

}
