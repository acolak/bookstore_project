package com.acolak.readingisgood.service;

import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.repository.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Service
public class OrderService {

	private OrderRepository orderRepository;
	private BookService bookService;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> getAllOrdersByCustomerId(String customerId, Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Order> orderWithPaged = orderRepository.findAllByCustomerId(customerId, paging);

		if(orderWithPaged.hasContent()){
			return orderWithPaged.getContent();
		}

		return new ArrayList<>();
	}

	public Order createOrder() {

		Order order = new Order();
		orderRepository.insert(order);

		return order;
	}
}
