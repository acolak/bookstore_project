package com.acolak.readingisgood.service;

import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.repository.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@Service
@Slf4j
public class StaticsService {

	private OrderRepository orderRepository;

	public StaticsService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> getCustomerMonthlyOrders(String customerId) {
		Optional<List<Order>> ordersRecord = orderRepository.findAllByCustomerId(customerId);

		if(ordersRecord.isPresent()) {
			List<Order> orders = ordersRecord.get();

			for(Order order : orders) {

			}

		}

		return null;
	}

	public Object convertOrdersToResponsDTO(List<Order> ordersByInterval) {

		return null;
	}
}
