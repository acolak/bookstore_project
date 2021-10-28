package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.repository.OrderRepository;
import com.acolak.readingisgood.repository.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/
@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

	@Spy
	@InjectMocks
	private StatisticsService statisticsService;

	@Mock
	private MongoTemplate mongoTemplate;


	@Test
	public void getCustomerMonthlyOrdersUnitTest() {
		List<OrderStatisticsDTO> orderStats = new ArrayList<>();
		OrderStatisticsDTO orderStatisticsDTO = new OrderStatisticsDTO();
		orderStatisticsDTO.setTotalOrderCount(21);
		orderStatisticsDTO.setMonth("MAY");
		orderStatisticsDTO.setTotalBookCount(11);
		orderStatisticsDTO.setTotalPurchasedAmount(500d);
		orderStats.add(orderStatisticsDTO);

		//lenient().when(mongoTemplate.aggregate(any(Aggregation.class), any(Order.class), any(OrderStatisticsDTO.class)).thenReturn();

		statisticsService.findMonthlyOrderStats(anyString());


	}

}
