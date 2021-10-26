package com.acolak.readingisgood.repository;

import com.acolak.readingisgood.repository.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author AhmetColak date 25.10.2021 Copyright © 2021.
 **/
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Optional<Customer> findCustomerByEmail(String email);

}
