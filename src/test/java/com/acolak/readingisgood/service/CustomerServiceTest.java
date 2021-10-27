package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.customer.CustomerRequestDTO;
import com.acolak.readingisgood.dto.customer.CustomerResponseDTO;
import com.acolak.readingisgood.exception.CustomerServiceException;
import com.acolak.readingisgood.repository.CustomerRepository;
import com.acolak.readingisgood.repository.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Spy
	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;



	@Test
	public void getCustomerByIdTestWhenCustomerNotFound() {

		when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

		CustomerServiceException thrown = assertThrows(
				CustomerServiceException.class,
				() -> customerService.getCustomerById(anyString()));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Customer Not Found!"));
	}

	@Test
	public void getCustomerByIdTest() {

		Customer customer = new Customer();
		customer.setCustomerId("21321");
		lenient().when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));
		assertNotNull(customerService.getCustomerById(anyString()));
	}

	@Test
	public void addCustomerUnitTest() {

		CustomerRequestDTO requestDTO = new CustomerRequestDTO();
		requestDTO.setEmail("ahmtcolak@gmail.com");
		lenient().when(customerRepository.findCustomerByEmail(anyString())).thenReturn(Optional.empty());
		Customer customer = customerService.addCustomer(requestDTO);
		assertNotNull(customer);
		verify(customerRepository).insert(any(Customer.class));
	}

	@Test
	public void addCustomerWhenAlreadyExistUnitTest() {

		Customer customer = new Customer();
		customer.setCustomerId("21321");
		CustomerRequestDTO requestDTO = new CustomerRequestDTO();
		requestDTO.setEmail("ahmtcolak@gmail.com");
		lenient().when(customerRepository.findCustomerByEmail(anyString())).thenReturn(Optional.of(customer));

		CustomerServiceException thrown = assertThrows(
				CustomerServiceException.class,
				() -> customerService.addCustomer(requestDTO));

		assertTrue(thrown.getErrorMessage().equalsIgnoreCase("Customer Already Exist!"));

	}

	@Test
	public void convertToCustomerResponseDTOUnitTest() {

		Customer customer = new Customer();
		customer.setCustomerId("21321");
		customer.setEmail("ahmtcolak@gmail.com");
		CustomerResponseDTO responseDTO = customerService.convertToCustomerResponseDTO(customer);

		assertNotNull(responseDTO);
		assertTrue(responseDTO.getEmail().equalsIgnoreCase("ahmtcolak@gmail.com"));
	}

	@Test
	public void buildCustomerEntityUnitTest() {

		CustomerRequestDTO requestDTO = new CustomerRequestDTO();
		requestDTO.setEmail("ahmtcolak@gmail.com");
		Customer customer = customerService.buildCustomerEntity(requestDTO);

		assertNotNull(customer);
		assertTrue(customer.getEmail().equalsIgnoreCase("ahmtcolak@gmail.com"));
	}


}
