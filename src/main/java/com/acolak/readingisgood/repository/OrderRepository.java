package com.acolak.readingisgood.repository;

import com.acolak.readingisgood.repository.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
public interface OrderRepository extends MongoRepository<Order, String> {

	Page<Order> findAllByCustomerId(String customerId, Pageable pageable);

	Optional<List<Order>> findAllByCreateDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	Optional<List<Order>> findAllByCustomerId(String customerId);

}
