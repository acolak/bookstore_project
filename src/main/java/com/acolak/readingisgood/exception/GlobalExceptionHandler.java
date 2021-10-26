package com.acolak.readingisgood.exception;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handling In application
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(BookAlreadyExistException.class)
	public ResponseEntity<ErrorBody> handleBookAlreadyExistException(BookAlreadyExistException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), "Book Already Exist!");
		log.error(exception.getErrorCode() + " Book Already Exist!");
		return new ResponseEntity<>(errorBody, responseStatus);
	}

}
