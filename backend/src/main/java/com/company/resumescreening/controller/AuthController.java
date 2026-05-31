package com.company.resumescreening.controller;

import com.company.resumescreening.dto.request.LoginRequest;
import com.company.resumescreening.dto.response.LoginResponse;
import com.company.resumescreening.service.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Temporary hardcoded credentials
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return new LoginResponse(token);
        }
        throw new RuntimeException("Invalid credentials");
    }
}

