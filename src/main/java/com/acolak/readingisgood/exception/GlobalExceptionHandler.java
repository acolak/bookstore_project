package com.acolak.readingisgood.exception;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handling In application
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(BookServiceException.class)
	public ResponseEntity<ErrorBody> handleBookAlreadyExistException(BookServiceException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), exception.getErrorMessage());
		log.error(exception.getErrorCode() + "-" + exception.getErrorMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler(CustomerServiceException.class)
	public ResponseEntity<ErrorBody> handleCustomerServiceException(CustomerServiceException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), exception.getErrorMessage());
		log.error(exception.getErrorCode() + "-" + exception.getErrorMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorBody> handleBadCredentialsException(BadCredentialsException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(401, exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

}
