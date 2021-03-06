package com.acolak.readingisgood.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Data
@AllArgsConstructor
public class ErrorBody {

	private int errorCode;
	private String errorDetail;
}
