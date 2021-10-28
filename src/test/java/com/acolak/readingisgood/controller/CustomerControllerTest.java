package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.dto.customer.CustomerRequestDTO;
import com.acolak.readingisgood.dto.customer.CustomerResponseDTO;
import com.acolak.readingisgood.repository.entity.Customer;
import com.acolak.readingisgood.service.CustomerService;
import com.acolak.readingisgood.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Mock
	private CustomerService customerService;

	@Mock
	private OrderService orderService;

	@Test
	public void registerNewCustomerUnitTest() {
		Customer customer = new Customer();
		customer.setEmail("ahmtcolak@gmail.com");
		when(customerService.addCustomer(any(CustomerRequestDTO.class))).thenReturn(customer);

		CustomerResponseDTO responseDTO = new CustomerResponseDTO();
		when(customerService.convertToCustomerResponseDTO(customer)).thenReturn(responseDTO);

		CustomerRequestDTO customerRequestDTO  = new CustomerRequestDTO();
		ResponseEntity responseEntity = customerController.registerNewCustomer(customerRequestDTO);

		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void getAllOrdersByCustomerIdUnitTest() {
		lenient().when(orderService.getAllOrdersByCustomerId(anyString(), anyInt(), anyInt(), anyString())).thenReturn(anyList());

		ResponseEntity responseEntity = customerController.getAllOrdersByCustomerId(eq("12345"), eq(1), eq(10), "createDate");

		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

}
