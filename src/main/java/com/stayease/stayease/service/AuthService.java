package com.stayease.stayease.service;

import com.stayease.stayease.dto.*;
import com.stayease.stayease.entity.*;
import com.stayease.stayease.repository.UserRepository;
import com.stayease.stayease.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.stayease.stayease.exception.CustomException;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        log.info("Register request received for email: {}", request.getEmail());

        Role role = request.getRole() == null ?
                Role.CUSTOMER :
                Role.valueOf(request.getRole());

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(role)
                .build();

        userRepository.save(user);

        log.info("User registered successfully: {}", user.getEmail());

        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request) {

        log.info("Login attempt for email: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("User not found: {}", request.getEmail());
                    return new CustomException("User not found");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.error("Invalid password attempt for email: {}", request.getEmail());
            throw new CustomException("Invalid credentials");
        }

        log.info("Login successful for email: {}", request.getEmail());

        return jwtService.generateToken(user);
    }
}
