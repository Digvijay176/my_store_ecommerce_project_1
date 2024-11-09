package com.production.mystore.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error->{
			String name = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(name, message);
		});
		return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
	}
}
