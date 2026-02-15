package com.stayease.stayease.controller;

import com.stayease.stayease.dto.LoginRequest;
import com.stayease.stayease.dto.RegisterRequest;
import com.stayease.stayease.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        String token = authService.register(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "User registered successfully",
                        "token", token
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "token", token
                )
        );
    }
}
