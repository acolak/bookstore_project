package com.acolak.readingisgood.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
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
