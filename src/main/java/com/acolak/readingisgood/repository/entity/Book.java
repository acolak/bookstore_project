package com.acolak.readingisgood.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Data
@Builder
@Document
public class Book {

	@Id
	private String bookId;

	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();
	private LocalDateTime updatedDate;
	private String name;
	private String author;
	private Long stock;
	private Double price;

}
