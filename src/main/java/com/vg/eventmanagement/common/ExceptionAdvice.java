package com.vg.eventmanagement.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(value= NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleGenricNotFoundException(NotFoundException e)
	{
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.OK.value()));
		return new ResponseEntity<>(error,HttpStatus.OK);
		
		
	}
}
