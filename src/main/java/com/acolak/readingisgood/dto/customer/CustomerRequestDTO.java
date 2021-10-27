package com.acolak.readingisgood.dto.customer;

import com.acolak.readingisgood.dto.BaseDTO;
import lombok.*;

/**
 * @author AhmetColak date 25.10.2021 Copyright © 2021.
 **/
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO extends BaseDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;

}
