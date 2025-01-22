package com.foodies.foodiesBackendImplementation.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
