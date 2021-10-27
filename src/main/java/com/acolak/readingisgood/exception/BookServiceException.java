package com.acolak.readingisgood.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceException extends RuntimeException{

	private int errorCode;
	private String errorMessage;

}
