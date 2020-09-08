package com.microservices.movies.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8801863120082664703L;

	public MovieNotFoundException() {

	}

	public MovieNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovieNotFoundException(String message) {
		super(message);
	}

	public MovieNotFoundException(Throwable cause) {
		super(cause);
	}
}
