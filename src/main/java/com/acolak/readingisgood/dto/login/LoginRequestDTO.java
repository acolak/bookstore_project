package com.acolak.readingisgood.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

	private String userName;
	private String password;

}
