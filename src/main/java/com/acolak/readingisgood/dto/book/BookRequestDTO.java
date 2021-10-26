package com.acolak.readingisgood.dto.book;

import com.acolak.readingisgood.dto.BaseDTO;
import lombok.Builder;
import lombok.Data;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Data
@Builder
public class BookRequestDTO extends BaseDTO {

	private String name;
	private String author;
	private Double price;
	private Long stock;
}
