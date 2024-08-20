package com.amit.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.security.jwt.JwtUtils;

@RestController
public class SecurityController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/admin")
	public String admin() {

		return "Hi Admin !!";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String user() {

		return "Hi User !!";
	}

}
