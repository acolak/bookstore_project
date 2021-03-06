package com.acolak.readingisgood.dto.customer;

import com.acolak.readingisgood.dto.BaseDTO;
import com.acolak.readingisgood.dto.BaseResponseDTO;
import lombok.*;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO extends BaseResponseDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String customerId;

}
