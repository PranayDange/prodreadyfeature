package com.example.learnprodreadyfeature.prodreadyfeature.controller;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.LoginDto;
import com.example.learnprodreadyfeature.prodreadyfeature.dto.SignUpDto;
import com.example.learnprodreadyfeature.prodreadyfeature.dto.UserDto;
import com.example.learnprodreadyfeature.prodreadyfeature.services.AuthService;
import com.example.learnprodreadyfeature.prodreadyfeature.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        String token = authService.loginUser(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        // cookie.setSecure(); this should be only used by https request
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
