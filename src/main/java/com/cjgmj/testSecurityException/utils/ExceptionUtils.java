package com.cjgmj.testSecurityException.utils;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cjgmj.testSecurityException.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class ExceptionUtils {

	private static HttpServletRequest request;

	@Autowired
	private void setRequest(HttpServletRequest request) {
		ExceptionUtils.request = request;
	}

	public static ExceptionResponse getResponse(String message) {
		ExceptionResponse exception = new ExceptionResponse();

		exception.setMessage(message);
		exception.setTimestamp(LocalDateTime.now());
		exception.setPath(request.getRequestURI());

		return exception;
	}

	public static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return mapper;
	}
}
