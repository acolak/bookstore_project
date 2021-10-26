package com.acolak.readingisgood.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 25.10.2021 Copyright © 2021.
 **/
@Data
@Document
public class Customer {

	@Id
	private String customerId;
	private String firstName;
	private String lastName;

	@Indexed(unique = true)
	private String email;
	private String phone;
	private LocalDateTime createDate;

}
