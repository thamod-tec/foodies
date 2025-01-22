package com.foodies.foodiesBackendImplementation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;

    
    
}
