package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.exception.OrderServiceException;
import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.repository.entity.Order;
import com.acolak.readingisgood.util.ConvertingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Service
@Slf4j
public class StatisticsService {

	private MongoTemplate mongoTemplate;

	public StatisticsService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<OrderStatisticsDTO> findMonthlyOrderStats(String customerId) {

		final Aggregation aggregation = newAggregation(
				project("amount", "totalPrice").and(DateOperators.Month.month("$createDate")).as("month"),
				group("month").count().as("totalOrderCount").sum("amount").as("totalBookCount").sum("totalPrice").as("totalPurchasedAmount"),
				project("totalOrderCount", "totalBookCount", "totalPurchasedAmount").and("month").previousOperation(),
				sort(Sort.Direction.DESC, "month"));

		AggregationResults<OrderStatisticsDTO> aggregationResults = mongoTemplate.aggregate(aggregation, Order.class, OrderStatisticsDTO.class);

		if (aggregationResults.getMappedResults().isEmpty()) {
			throw new OrderServiceException(610, "Order Statistics Could not be found");
		}

		List<OrderStatisticsDTO> orderStats = aggregationResults.getMappedResults();
		for (OrderStatisticsDTO order : orderStats) {
			int id = Integer.parseInt(order.getMonth());
			order.setMonth(ConvertingUtils.convertMonthIdToName(id));
		}

		return aggregationResults.getMappedResults();
	}

}
