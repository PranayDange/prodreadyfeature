package com.example.learnprodreadyfeature.prodreadyfeature.services;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.LoginDto;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPass()));
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
