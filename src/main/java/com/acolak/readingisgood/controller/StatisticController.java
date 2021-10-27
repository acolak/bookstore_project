package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.repository.entity.Order;
import com.acolak.readingisgood.service.StaticsService;
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

	private StaticsService staticsService;

	public StatisticController(StaticsService staticsService) {
		this.staticsService = staticsService;
	}

	@GetMapping("/customer-monthly-orders/{customerId}")
	public ResponseEntity<?> listCustomerMonthlyOrders(@PathVariable String customerId){
		List<Order> ordersByInterval = staticsService.getCustomerMonthlyOrders(customerId);

		return ResponseEntity.ok(staticsService.convertOrdersToResponsDTO(ordersByInterval));
	}

}
