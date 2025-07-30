package com.votezy.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		ErrorResponse err=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		
		return new ResponseEntity<>(err , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex){
		ErrorResponse err=new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
		
		return new ResponseEntity<>(err , HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(VoteNotAllowedException.class)
	public ResponseEntity<ErrorResponse> handleVoteNotAllowedExceptionException(VoteNotAllowedException ex){
		ErrorResponse err=new ErrorResponse(HttpStatus.FORBIDDEN.value(),ex.getMessage());
		
		return new ResponseEntity<>(err , HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors=new HashMap<String, String>();
		BindingResult bindingResult=ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		
		for (FieldError fieldError : fieldErrors) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex){
		ErrorResponse err=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
		
		return new ResponseEntity<>(err , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
