package com.voting_semulation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.voting_semulation.dtos.DTOs.AuthResponce;
import com.voting_semulation.dtos.DTOs.LoginRequest;
import com.voting_semulation.dtos.DTOs.RegisterRequest;
import com.voting_semulation.entity.Role;
import com.voting_semulation.entity.User;
import com.voting_semulation.globlalException.ApiException;
import com.voting_semulation.repository.RoleRepository;
import com.voting_semulation.repository.UserRepository;
import com.voting_semulation.security.JwtUtils;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtUtils jwtUtils;

	// methods for register

	public AuthResponce register(RegisterRequest req) {

		if (userRepository.existsByEmail(req.email())) {
			throw new ApiException(HttpStatus.CONFLICT, "Email Exist ", "Email is already exist:");

		}

		String targetRole = (req.role() == null || req.role().isBlank()) ? "ROLE_VOTER" : req.role();

		
		
		Role role = roleRepository.findByName(targetRole)
				
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "ROLE_NOT_FOUND", "Role Invalid"));

	
		 String encodedPassword =
	                encoder.encode(req.password());

		 
		User user = new User(req.name(), req.email(), encodedPassword, role);
		
		
		  User savedUser = userRepository.save(user);


		String token = jwtUtils.generateToken(savedUser.getEmail(), savedUser.getRole().getName());
		

		return new AuthResponce(token, savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole().getName());

	}
	
	public AuthResponce login (LoginRequest req) {
		
		User user = userRepository.findByEmail(req.email())
				.orElseThrow(()-> new ApiException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "Invalid credentials "));
		
		if(!encoder.matches(req.password(), user.getPassword())) {
		throw new ApiException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", "Invalid Credential ");	
		}
		
		
		
		if(!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
			throw new ApiException(HttpStatus.FORBIDDEN,"ACCOUNT_LOCKED","Accound is not active ");
		}
		
		String token = jwtUtils.generateToken(user.getEmail(), user.getRole().getName());
		
		return new AuthResponce(token, user.getId(), user.getName(), user.getEmail(), user.getRole().getName());
		
	}
	
	
	
	

}
