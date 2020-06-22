package com.iotnetwork.platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends Exception {

	public AuthenticationException() {
		super();

	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);

	}

	public AuthenticationException(String message) {
		super(message);

	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

}
