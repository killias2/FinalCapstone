package com.techelevator.model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "The original password is not correct for this user")
public class WrongPasswordChangePasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5395135654808102743L;

}
