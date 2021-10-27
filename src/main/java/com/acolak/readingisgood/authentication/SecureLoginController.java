package com.acolak.readingisgood.authentication;

import com.acolak.readingisgood.constant.ControllerConstants;
import com.acolak.readingisgood.dto.login.LoginRequestDTO;
import com.acolak.readingisgood.dto.login.LoginResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@RestController
@RequestMapping(ControllerConstants.LOGIN_URL)
@Slf4j
public class SecureLoginController {

	private AuthenticationManager authenticationManager;
	private UserService userService;
	private JwtUtils jwtUtils;

	public SecureLoginController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody LoginRequestDTO requestDTO) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUserName(), requestDTO.getPassword()));
		} catch (BadCredentialsException badCredentialsException) {
			throw new BadCredentialsException("Incorrect user name or password");
		}

		final UserDetails userDetails = userService.loadUserByUsername(requestDTO.getUserName());
		final String loginToken = jwtUtils.generateToken(userDetails);

		return ResponseEntity.ok(new LoginResponseDTO(loginToken));

	}
}