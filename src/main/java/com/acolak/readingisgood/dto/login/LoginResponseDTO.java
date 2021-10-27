package com.acolak.readingisgood.dto.login;

import com.acolak.readingisgood.dto.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Data
@AllArgsConstructor
public class LoginResponseDTO extends BaseResponseDTO {

	private String loginToken;

}
