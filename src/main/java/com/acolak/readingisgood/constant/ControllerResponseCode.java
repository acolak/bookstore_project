package com.acolak.readingisgood.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ControllerResponseCode {

	public final static ControllerResponseCode CUSTOMER_ALREADY_DEFINED = new ControllerResponseCode("GTR-105", "Customer Already Exist!", HttpStatus.CONFLICT);

	public final static ControllerResponseCode USER_NOT_AUTHENTICATED = new ControllerResponseCode("GTR-401", "User Not Authenticated!", HttpStatus.UNAUTHORIZED);
	public final static ControllerResponseCode BAD_REQUEST = new ControllerResponseCode("GTR-002", "Paramater Not Suitable!", HttpStatus.BAD_REQUEST);

	@JsonValue
	private final String code;

	@Nullable
	private String message;

	@JsonIgnore
	private HttpStatus httpStatus;

	public ControllerResponseCode(String code, HttpStatus httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}

}
