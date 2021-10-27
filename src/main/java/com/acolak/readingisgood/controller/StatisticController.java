package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.dto.statistics.StatisticsResponseDTO;
import com.acolak.readingisgood.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@RestController
@RequestMapping(ControllerConstants.STATISTICS_URL)
public class StatisticController {

	private StatisticsService statisticsService;

	public StatisticController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	@GetMapping("/customer-monthly-orders/{customerId}")
	public ResponseEntity<?> listCustomerMonthlyOrders(@PathVariable String customerId){
		List<OrderStatisticsDTO> orderStatisticsDTOS = statisticsService.getCustomerMonthlyOrders(customerId);

		StatisticsResponseDTO responseDTO = new StatisticsResponseDTO();
		responseDTO.setOrderStatisticsDTOList(orderStatisticsDTOS);
		return ResponseEntity.ok(responseDTO);
	}

}
