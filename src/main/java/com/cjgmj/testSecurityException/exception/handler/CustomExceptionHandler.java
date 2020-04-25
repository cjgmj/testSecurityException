package com.cjgmj.testSecurityException.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cjgmj.testSecurityException.exception.DefaultException;
import com.cjgmj.testSecurityException.exception.ForbiddenException;
import com.cjgmj.testSecurityException.exception.NotFoundException;
import com.cjgmj.testSecurityException.utils.ExceptionUtils;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DefaultException.class)
	public final ResponseEntity<Object> handleRestExceptions(DefaultException ex) {
		return new ResponseEntity<Object>(ExceptionUtils.getResponse(ex.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleRestNotFoundExceptions(NotFoundException ex) {
		return new ResponseEntity<Object>(ExceptionUtils.getResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ForbiddenException.class)
	public final ResponseEntity<Object> handleUnauthorizedExceptions(ForbiddenException ex) {
		return new ResponseEntity<Object>(ExceptionUtils.getResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(ExceptionUtils.getResponse(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(ExceptionUtils.getResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(ExceptionUtils.getResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
