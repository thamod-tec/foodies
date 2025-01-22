package com.foodies.foodiesBackendImplementation.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodies.foodiesBackendImplementation.model.AuthRequest;
import com.foodies.foodiesBackendImplementation.model.AuthResponse;
import com.foodies.foodiesBackendImplementation.model.User;
import com.foodies.foodiesBackendImplementation.repository.SecurityRepository;
import com.foodies.foodiesBackendImplementation.repository.UserRepository;
import com.foodies.foodiesBackendImplementation.service.JwtService;
import com.foodies.foodiesBackendImplementation.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));;

        if (user != null && user.getPassword().equals(authRequest.getPassword())) { // Replace with hashed password check
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), "Login successful!"));
        } else {
            return ResponseEntity.status(401).body(new AuthResponse(null, null, "Invalid credentials."));
        }
    }
}
