package com.app.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payload.JwtAuthResponse;
import com.app.blog.payload.LoginDto;
import com.app.blog.payload.RegisterDto;
import com.app.blog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	// Build Login or SignIn REST API
	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setAccessToken(token);
		return ResponseEntity.ok(authResponse);
	}
	
	// Build Register or Signup REST API
	@PostMapping(value = { "/register", "/signup" })
	public ResponseEntity<String> login(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
}
