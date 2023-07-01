package com.nitesh.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nitesh.springboot.repo.UserRepo;

@Configuration
public class ApplicationConfig {

	@Autowired
	private UserRepo repo;
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> repo.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));		
	}
	
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
