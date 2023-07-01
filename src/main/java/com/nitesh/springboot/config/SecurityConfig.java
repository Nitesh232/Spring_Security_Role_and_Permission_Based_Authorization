package com.nitesh.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nitesh.springboot.entity.Permission;
import com.nitesh.springboot.entity.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf.disable()
				.authorizeHttpRequests.requestMatchers("/api/v1/auth/**").permitAll()
				
				.authorizeHttpRequests.requestMatchers("/api/v1/management/**", "/api/v1/demo/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.MANAGER_READ.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.MANAGER_CREATE.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.MANAGER_UPDATE.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.MANAGER_DELETE.name())
				
				
				.authorizeHttpRequests.requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_READ.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_CREATE.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_UPDATE.name())
				.authorizeHttpRequests.requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_DELETE.name())
				
	
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				
				.build();
	}
	
	
}
