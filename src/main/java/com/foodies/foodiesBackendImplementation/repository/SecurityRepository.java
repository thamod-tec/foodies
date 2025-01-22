package com.foodies.foodiesBackendImplementation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.foodies.foodiesBackendImplementation.model.User;

public interface SecurityRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}


