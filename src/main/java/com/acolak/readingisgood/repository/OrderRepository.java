package com.acolak.readingisgood.repository;

import com.acolak.readingisgood.repository.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
public interface OrderRepository extends MongoRepository<Order, String> {

	Page<Order> findAllByCustomerId(String customerId, Pageable pageable);

}
