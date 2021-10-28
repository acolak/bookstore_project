package com.acolak.readingisgood.controller;

import com.acolak.readingisgood.dto.statistics.OrderStatisticsDTO;
import com.acolak.readingisgood.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author AhmetColak date 28.10.2021 Copyright © 2021.
 **/
@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {

	@Spy
	@InjectMocks
	private StatisticController statisticController;

	@Mock
	private StatisticsService statisticsService;

	@Test
	public void listCustomerMonthlyOrdersUniTest() {

		List<OrderStatisticsDTO> orderStatisticsDTOList  = new ArrayList<>();
		lenient().when(statisticsService.findMonthlyOrderStats(anyString())).thenReturn(orderStatisticsDTOList);

		ResponseEntity responseEntity = statisticController.getCustomerMonthlyOrders("12345");

		assertNotNull(responseEntity);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
	}

}
