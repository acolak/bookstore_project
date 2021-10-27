package com.acolak.readingisgood.dto.statistics;

import com.acolak.readingisgood.dto.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class StatisticsResponseDTO extends BaseResponseDTO {

	List<OrderStatisticsDTO> orderStatisticsDTOList;

}
