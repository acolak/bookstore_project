package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.dto.customer.CustomerRequestDTO;
import com.acolak.readingisgood.dto.customer.CustomerResponseDTO;
import com.acolak.readingisgood.repository.entity.Customer;
import com.acolak.readingisgood.repository.entity.Order;
import com.acolak.readingisgood.service.CustomerService;
import com.acolak.readingisgood.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AhmetColak date 25.10.2021 Copyright © 2021.
 **/
@RestController
@RequestMapping(ControllerConstants.CUSTOMER_URL)
public class CustomerController {

	private CustomerService customerService;
	private OrderService orderService;

	public CustomerController(CustomerService customerService, OrderService orderService) {
		this.customerService = customerService;
		this.orderService = orderService;
	}

	@PostMapping(ControllerConstants.REGISTER_URL)
	public ResponseEntity<?> registerNewCustomer(@RequestBody CustomerRequestDTO requestDTO) {

		Customer customer = customerService.addCustomer(requestDTO);

		return ResponseEntity.ok(customerService.convertToCustomerResponseDTO(customer));

	}

	@GetMapping("/orders/{customerId}")
	public ResponseEntity<?> getAllOrdersByCustomerId(@PathVariable("customerId") String customerId, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "createDate") String sortBy) {

		List<Order> orders = orderService.getAllOrdersByCustomerId(customerId, pageNo, pageSize, sortBy);
		return ResponseEntity.ok(orders);
	}

}
