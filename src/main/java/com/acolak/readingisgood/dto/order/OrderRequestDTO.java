package com.acolak.readingisgood.dto.order;

import com.acolak.readingisgood.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO extends BaseDTO {

	@NotNull(message = "Please send customer info.")
	private String customerId;
	@NotNull(message = "Please send book info.")
	private String bookId;
	@NotNull(message = "Please send amount info.")
	private Long amount;

}
