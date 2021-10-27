package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.dto.order.OrderDTO;
import com.acolak.readingisgood.dto.order.OrderRequestDTO;
import com.acolak.readingisgood.dto.order.OrderResponseDTO;
import com.acolak.readingisgood.repository.entity.Order;
import com.acolak.readingisgood.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@RestController
@RequestMapping(ControllerConstants.ORDER_URL)
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequestDTO requestDTO){
		OrderDTO newOrder = orderService.createOrder(requestDTO);
		OrderResponseDTO responseDTO = new OrderResponseDTO();
		responseDTO.setOrder(newOrder);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<?> getOrderById(@PathVariable("orderId") String orderId){
		Order order = orderService.getOrderById(orderId);
		return ResponseEntity.ok(orderService.convertToOrderDTO(order));
	}


	@GetMapping("/list")
	public ResponseEntity<?> listOrdersByDateInterval(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
												 ){
		List<Order> ordersByInterval = orderService.getOrdersByDateInterval(startDate, endDate);

		return ResponseEntity.ok(orderService.convertOrdersToResponsDTO(ordersByInterval));

	}

}
