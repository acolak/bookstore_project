package com.acolak.readingisgood.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				// this disables session creation on Spring Security //TODO remove
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		http.headers().xssProtection();
		http.headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'"));

	}

}
