package com.example.controllers;

import com.example.dto.requests.RegisterUserDto;
import com.example.dto.responses.AuthResponseDto;
import com.example.dto.responses.ResponseHandler;
import com.example.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<ResponseHandler<AuthResponseDto>> registerUser(@RequestBody RegisterUserDto registerUserDto) throws Exception {
        return authService.registerUser(registerUserDto);

    }
}
