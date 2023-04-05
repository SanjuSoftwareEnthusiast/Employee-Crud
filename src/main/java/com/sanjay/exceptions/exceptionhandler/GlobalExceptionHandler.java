package com.sanjay.exceptions.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sanjay.exceptions.userdefinedexception.EmployeeNotFoundException;
import com.sanjay.payloads.EmployeeErrorResponse;
import com.sanjay.payloads.ValidationErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> employeeExceptionHandler(EmployeeNotFoundException ex)
	{
		EmployeeErrorResponse response = EmployeeErrorResponse.builder()
									     .message(ex.getMessage())
									     .status(HttpStatus.NOT_FOUND)
									     .build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(field, message);
		});

		ValidationErrorResponse response = new ValidationErrorResponse(errors, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
