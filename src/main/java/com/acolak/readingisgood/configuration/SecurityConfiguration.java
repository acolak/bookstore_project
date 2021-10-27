package com.acolak.readingisgood.configuration;

import com.acolak.readingisgood.authentication.JwtAuthorizationFilter;
import com.acolak.readingisgood.authentication.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import static com.acolak.readingisgood.constant.ControllerConstants.LOGIN_URL;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private JwtAuthorizationFilter jwtAuthorizationFilter;
	private UserService userService;

	public SecurityConfiguration(JwtAuthorizationFilter jwtAuthorizationFilter, UserService userService) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
		this.userService = userService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeRequests().antMatchers(LOGIN_URL + "/authenticate",
												 // -- Swagger UI v3 (OpenAPI)
												 "/v3/api-docs/**",
												 "/swagger-ui/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}


}
