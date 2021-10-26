package com.acolak.readingisgood.service;

import com.acolak.readingisgood.repository.entity.Customer;
import com.acolak.readingisgood.repository.CustomerRepository;
import org.springframework.stereotype.Service;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void addCustomer() {

		Customer customer = new Customer();

		customer.setEmail("test@gmail.com");
		customer.setPhone("5431322131");
		customer.setLastName("colak");
		customer.setFirstName("ahmet");
		customerRepository.insert(customer);

	}

}
