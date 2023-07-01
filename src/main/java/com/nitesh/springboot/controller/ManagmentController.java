package com.nitesh.springboot.controller;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
public class ManagmentController {

	

	@GetMapping
	public String get() {
		return "Get() method from management controller";
	}
	
	
	
	@PostMapping
	public String post() {
		return "Post() method from management controller";
	}

	
	
	@PutMapping
	public String put() {
		return "Put() method from management controller";
	}

	
	
	@DeleteMapping
	public String delete() {
		return "Delete() method from management controller";
	}

	
	
}
