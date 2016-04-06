package com.chenningzhang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * The Exception that will be thrown when a user with given id
 * does not exist in the database.
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
