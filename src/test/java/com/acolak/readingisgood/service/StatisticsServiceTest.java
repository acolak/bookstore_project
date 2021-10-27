package com.acolak.readingisgood.service;

import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
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
	private OrderRepository orderRepository;


	@Test
	public void getCustomerMonthlyOrdersUnitTest() {
		List<OrderStatisticsDTO> orderStats = new ArrayList<>();
		OrderStatisticsDTO orderStatisticsDTO = new OrderStatisticsDTO();
		orderStatisticsDTO.setOrderCount(21);
		orderStatisticsDTO.setMonth("MAY");
		orderStatisticsDTO.setTotalBookCount(11);
		orderStatisticsDTO.setTotalAmount(500d);
		orderStats.add(orderStatisticsDTO);


		lenient().when(orderRepository.orderStatsByMonthly()).thenReturn(any(AggregationResults.class));
		when(orderRepository.orderStatsByMonthly().getMappedResults()).thenReturn(orderStats);

		statisticsService.getCustomerMonthlyOrders(anyString());


	}

}
