package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.dto.statistics.MonthDTO;
import com.acolak.readingisgood.exception.OrderServiceException;
import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.util.ConvertingUtils;
import lombok.extern.slf4j.Slf4j;
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

	private OrderRepository orderRepository;
	private MongoTemplate mongoTemplate;

	public StatisticsService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
		this.orderRepository = orderRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public List<OrderStatisticsDTO> getCustomerMonthlyOrders(String customerId) {
		List<OrderStatisticsDTO> orderStats = orderRepository.orderStatsByMonthly().getMappedResults();

		List<MonthDTO> orderMonthsDtoList = groupOrderMonths();

		int months = 0;
		for(OrderStatisticsDTO order : orderStats){
			int id = orderMonthsDtoList.get(months).getMonth();
			order.setMonth(ConvertingUtils.convertMonthIdToName(id));
			months++;
		}

		if(orderStats.isEmpty()){
			throw new OrderServiceException(610, "Order Statistics Could not be found");
		}
		log.info(orderStats.toString());
		return orderStats;
	}

	public List<MonthDTO> groupOrderMonths(){

		Aggregation agg = newAggregation(
				project("orderId").and(DateOperators.Month.month("$createDate")).as("month"),
				group("month").count().as("orderCount"),
				project("orderCount").and("month").previousOperation());

		AggregationResults<MonthDTO> results = mongoTemplate.aggregate(agg, "orders", MonthDTO.class);
		List<MonthDTO> orderMonthList = results.getMappedResults();

		log.info(orderMonthList.toString());

		return orderMonthList;
	}


}
