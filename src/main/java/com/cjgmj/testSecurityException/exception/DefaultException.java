package com.cjgmj.testSecurityException.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DefaultException extends RuntimeException {

	private static final long serialVersionUID = 2492312423068587708L;

	public DefaultException(String message, Throwable cause) {
		super(message, cause);
	}

	public DefaultException(String message) {
		super(message);
	}

	public DefaultException(Throwable cause) {
		super(cause);
	}
}
