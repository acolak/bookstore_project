package com.acolak.readingisgood.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author AhmetColak date 27.10.2021 Copyright © 2021.
 **/
@Service
public class UserService implements UserDetailsService {

	@Value("${auth.userName}")
	private String clientName;

	@Value("${auth.userPass}")
	private String clientPassword;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new User(clientName, clientPassword, new ArrayList<>());
	}
}