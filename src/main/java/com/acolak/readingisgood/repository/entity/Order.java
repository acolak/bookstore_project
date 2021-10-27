package com.acolak.readingisgood.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/

@Data
@Document
@Builder
public class Order {

	@Id
	private String orderId;

	@Indexed
	private String customerId;
	private String bookId;
	private Long amount;
	private Double totalPrice;

	@Indexed
	@Builder.Default
	LocalDateTime createDate = LocalDateTime.now();

}
