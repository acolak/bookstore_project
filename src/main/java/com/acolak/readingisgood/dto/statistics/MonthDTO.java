package com.acolak.readingisgood.dto.statistics;

import lombok.Data;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@Data
public class MonthDTO {

	private String totalOrderCount;
	private String totalBookCount;
	private String totalPurchasedAmount;
}
