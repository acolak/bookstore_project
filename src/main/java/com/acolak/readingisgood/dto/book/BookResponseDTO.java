package com.acolak.readingisgood.dto.book;

import com.acolak.readingisgood.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO extends BaseDTO {

	private String name;
	private String author;
	private Double price;
	private Long stock;
}
