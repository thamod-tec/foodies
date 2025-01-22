package com.foodies.foodiesBackendImplementation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.foodies.foodiesBackendImplementation.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public boolean validateToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }
}
