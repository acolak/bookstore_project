package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;

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

	@PostMapping("/add")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
		Order newOrder = orderService.addOrder(orderRequest);
		return ResponseEntity.ok(orderService.convertToOrderDto(newOrder));
	}

	@GetMapping("/orders/{orderId)")
	public ResponseEntity<?> placeOrder(@PathVariable("orderId") String orderId, @RequestBody OrderRequest orderRequest){
		Order newOrder = orderService.addOrder(orderRequest);
		return ResponseEntity.ok(orderService.convertToOrderDto(newOrder));
	}


	@GetMapping("/list")
	public ResponseEntity<?> listOrdersByInterval(
			@PathParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@PathParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
												 ){
		List<Order> ordersByInterval = orderService.getOrdersByDateInterval(startDate, endDate);
		orderService.groupedOrdersByMonth();
		return ResponseEntity.ok(ordersByInterval);

	}

}
