package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.dto.order.OrderDTO;
import com.acolak.readingisgood.dto.order.OrderRequestDTO;
import com.acolak.readingisgood.repository.entity.Order;
import com.acolak.readingisgood.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/

@ExtendWith(MockitoExtension.class)
public class OrderControllerUnitTest {

	@Spy
	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderService;

	@Test
	public void orderControllerUnitTest() {

		Order order = new Order();
		order.setOrderId("12313");
		when(orderService.getOrderById(anyString())).thenReturn(order);

		ResponseEntity responseEntity  = orderController.getOrderById("12345");

		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));

	}

	@Test
	public void createOrderUnitTest() {

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId("12313");
		orderDTO.setAmount(3L);
		when(orderService.createOrder(any(OrderRequestDTO.class))).thenReturn(orderDTO);

		OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
		orderRequestDTO.setCustomerId("123");
		orderRequestDTO.setBookId("456");
		orderRequestDTO.setAmount(3L);
		ResponseEntity responseEntity  = orderController.createOrder(orderRequestDTO);

		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));

	}

	@Test
	public void listOrdersByDateIntervalUnitTest() {

		List<Order> orders = new ArrayList<>();
		lenient().when(orderService.getOrdersByDateInterval(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(orders);

		ResponseEntity responseEntity = orderController.listOrdersByDateInterval(LocalDateTime.now(), LocalDateTime.now());

		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

}
