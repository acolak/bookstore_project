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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import static com.acolak.readingisgood.constant.ControllerResponseCode.BAD_REQUEST;
import static com.acolak.readingisgood.constant.ControllerResponseCode.USER_NOT_AUTHENTICATED;

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

	@ExceptionHandler(OrderServiceException.class)
	public ResponseEntity<ErrorBody> handleCustomerServiceException(OrderServiceException exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(exception.getErrorCode(), exception.getErrorMessage());
		log.error(exception.getErrorCode() + "-" + exception.getErrorMessage());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorBody> handleGeneralException(Exception exception) {
		HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorBody errorBody = new ErrorBody(500, "Unexcepted Error!");
		log.error(exception.getMessage() + " trace : "+ exception.getStackTrace().toString());
		return new ResponseEntity<>(errorBody, responseStatus);
	}

	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<Object> handleAuthenticationException(HttpServletRequest request,AuthenticationException ex){
		return new ResponseEntity<>(USER_NOT_AUTHENTICATED.getMessage(), USER_NOT_AUTHENTICATED.getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(HttpServletRequest request,MethodArgumentTypeMismatchException ex){
		return new ResponseEntity<>(BAD_REQUEST.getMessage(), BAD_REQUEST.getHttpStatus());
	}

}
