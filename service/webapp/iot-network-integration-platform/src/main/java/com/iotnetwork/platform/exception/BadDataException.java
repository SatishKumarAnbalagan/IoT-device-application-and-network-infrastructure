package com.iotnetwork.platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadDataException extends Exception {

	public BadDataException() {
		super();

	}

	public BadDataException(String message, Throwable cause) {
		super(message, cause);

	}

	public BadDataException(String message) {
		super(message);

	}

	public BadDataException(Throwable cause) {
		super(cause);
	}

}
