package com.acolak.readingisgood.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/

@Data
@Document
public class Order {

	@MongoId
	private String orderId;

	@Indexed
	private String customerId;
	private String bookId;
	private Integer amount;
	private Double totalPrice;

	@Indexed
	LocalDateTime createDate;

}
