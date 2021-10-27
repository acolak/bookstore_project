package com.acolak.readingisgood.dto.order;

import com.acolak.readingisgood.dto.BaseDTO;
import com.acolak.readingisgood.dto.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderResponseDTO extends BaseResponseDTO {

	private OrderDTO order;
	private List<OrderDTO> orderList;

}
