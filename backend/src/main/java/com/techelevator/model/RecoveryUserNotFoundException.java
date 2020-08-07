package com.techelevator.model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "User Doesn't Exist.")
public class RecoveryUserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6390606963554769817L;

}