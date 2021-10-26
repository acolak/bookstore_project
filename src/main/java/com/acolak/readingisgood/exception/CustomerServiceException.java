package com.acolak.readingisgood.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceException extends RuntimeException {

	private int errorCode;
	private String errorMessage;

}
