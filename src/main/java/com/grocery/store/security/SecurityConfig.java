package com.grocery.store.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	



	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	    httpSecurity.authorizeRequests()
	    .antMatchers("/").permitAll().and()
	    .authorizeRequests().antMatchers("/h2**").permitAll();
	    
	    httpSecurity.authorizeRequests()
	    .antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/login/**").permitAll();
	    httpSecurity.csrf().disable();
	    httpSecurity.headers().frameOptions().disable();
	}

}