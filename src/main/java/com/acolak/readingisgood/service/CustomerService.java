package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.customer.CustomerRequestDTO;
import com.acolak.readingisgood.dto.customer.CustomerResponseDTO;
import com.acolak.readingisgood.exception.CustomerServiceException;
import com.acolak.readingisgood.repository.CustomerRepository;
import com.acolak.readingisgood.repository.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/

@Service
@Slf4j
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerResponseDTO addCustomer(CustomerRequestDTO requestDTO) {

		Optional<Customer> customerRecord = customerRepository.findCustomerByEmail(requestDTO.getEmail());

		if(customerRecord.isPresent()) {
			throw new CustomerServiceException(604, "Customer Already Exist!");
		} else {
			Customer customer = buildCustomerEntity(requestDTO);
			customerRepository.insert(customer);
			return convertToCustomerResponseDTO(customer);
		}
	}

	@Transactional
	public Customer getCustomerById(String customerId) {

		Optional<Customer> customerRecord = customerRepository.findById(customerId);

		if(customerRecord.isPresent()) {
			Customer customer = customerRecord.get();
			log.info("getCustomerById is Successfully: " + customer);
			return customer;
		} else {
			throw new CustomerServiceException(605, "Customer Not Found!");
		}
	}

	public Customer buildCustomerEntity(CustomerRequestDTO requestDTO) {
		return Customer.builder()
				.firstName(requestDTO.getFirstName())
				.lastName(requestDTO.getLastName())
				.email(requestDTO.getEmail())
				.address(requestDTO.getAddress())
				.phone(requestDTO.getPhone())
				.build();
	}

	public CustomerResponseDTO convertToCustomerResponseDTO(Customer customer){
		return CustomerResponseDTO.builder()
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.email(customer.getEmail())
				.address(customer.getAddress())
				.phone(customer.getPhone())
				.customerId(customer.getCustomerId())
				.build();
	}

}
