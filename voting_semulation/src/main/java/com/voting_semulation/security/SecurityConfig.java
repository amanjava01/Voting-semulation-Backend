package com.voting_semulation.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	
	@Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration config = new CorsConfiguration();
    	config.setAllowedOriginPatterns(List.of("*"));
    	
    	
    	config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
    	
    	config.setAllowedHeaders(List.of("*"));
    	config.setAllowCredentials(true);
    	UrlBasedCorsConfigurationSource configSource= new UrlBasedCorsConfigurationSource();
    	configSource.registerCorsConfiguration("/**", config);
    	
    	return configSource;
    	
    }
    
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,AuthTokenFilter tokenFilter) throws Exception {
		
		
	http.cors(cors-> cors.configurationSource(corsConfigurationSource()))
		.csrf(AbstractHttpConfigurer::disable)
		.sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth-> auth.requestMatchers("/api/v1/auth/**").permitAll()
				                           .requestMatchers(HttpMethod.GET,"/api/v1/elections/**").authenticated()
				                           .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
				                           .anyRequest().authenticated()
				                           );
	http.addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class);
	return http.build();
		
	}
	
	
	
	
	
	
    
}
