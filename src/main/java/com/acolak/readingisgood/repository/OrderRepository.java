package com.acolak.readingisgood.repository;

import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.repository.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
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

	@Aggregation(pipeline = {
			"{\n" +
					"    $group: {\n" +
					"        _id: {\n" +
					"            month: {\n" +
					"                $month: $createDate\n" +
					"            }\n" +
					"        },\n" +
					"        orderCount: {\n" +
					"            $sum: 1\n" +
					"        },\n" +
					"        totalBookCount: {\n" +
					"            $sum: $amount\n" +
					"        },\n" +
					"        totalAmount: {\n" +
					"            $sum: $totalPrice\n" +
					"        }\n" +
					"    }\n" +
					"}, {\n" +
					"    $project: {\n" +
					"        'month': '$_id.month',\n" +
					"        '_id': 0,\n" +
					"        'orderCount': '$orderCount',\n" +
					"        'totalBookCount': '$totalBookCount',\n" +
					"        'totalAmount': '$totalAmount'\n" +
					"    }\n" +
					"}"
	})
	AggregationResults<OrderStatisticsDTO> monthlyStats();

}
