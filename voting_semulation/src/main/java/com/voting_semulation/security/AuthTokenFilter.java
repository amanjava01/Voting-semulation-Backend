package com.voting_semulation.security;

import com.voting_semulation.entity.User;
import com.voting_semulation.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header= request.getHeader("Authorization");
        if(header !=null && header.startsWith("Bearer ")){

            String jwt= header.substring(7);

            if(jwtUtils.validateToken(jwt)){

                String email= jwtUtils.getEmailFromToken(jwt);

                User user= userRepository.findByEmail(email).orElse(null);


                if(user!=null && "ACTIVE".equalsIgnoreCase(user.getStatus())){

                    var auth= new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(user.getRole().getName())));
                   auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                              SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }


            filterChain.doFilter(request, response);
        }




    }
}
