package com.acolak.readingisgood.util;

import lombok.Data;

/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/
@Data
public class ConvertingUtils {

	public static String convertMonthIdToName(int month){
		String[] monthNames = {"NaN", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return monthNames[month];
	}

}
