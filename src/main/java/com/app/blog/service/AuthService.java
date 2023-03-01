package com.app.blog.service;

import com.app.blog.payload.LoginDto;
import com.app.blog.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
}
