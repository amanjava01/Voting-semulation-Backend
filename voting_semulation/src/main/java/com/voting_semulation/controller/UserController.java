package com.voting_semulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting_semulation.dtos.DTOs.AuthResponce;
import com.voting_semulation.dtos.DTOs.LoginRequest;
import com.voting_semulation.dtos.DTOs.RegisterRequest;
import com.voting_semulation.service.AuthService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponce> register( @RequestBody RegisterRequest req)
	{
		return  ResponseEntity.ok(authService.register(req));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponce> login (@RequestBody LoginRequest req){
		return ResponseEntity.ok(authService.login(req));
	}

}
