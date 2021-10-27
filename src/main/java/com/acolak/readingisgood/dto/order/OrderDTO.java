package com.acolak.readingisgood.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
	private String orderId;
	private String customerId;
	private String bookId;
	private Long amount;
	private Double totalPrice;
	LocalDateTime created;
}
