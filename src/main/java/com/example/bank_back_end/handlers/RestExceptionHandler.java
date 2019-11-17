package com.example.bank_back_end.handlers;

import com.example.bank_back_end.exceptions.ResourceNotFoundException;
import com.example.bank_back_end.models.errors.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException( ResourceNotFoundException ex, HttpServletRequest request) {
		SimpleResponse errorDetail = new SimpleResponse();

		errorDetail.setCode(HttpStatus.NOT_FOUND.value());
		errorDetail.setMessage(ex.getMessage());

		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}


}
