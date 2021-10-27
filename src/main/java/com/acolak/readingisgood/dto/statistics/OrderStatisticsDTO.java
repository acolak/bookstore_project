package com.acolak.readingisgood.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatisticsDTO {

	private String month;
	private int orderCount;
	private int totalBookCount;
	private double totalAmount;

}
