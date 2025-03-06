package com.foodies.foodiesBackendImplementation.repository;

import com.foodies.foodiesBackendImplementation.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findByUserId(String userId);
    Optional<CartItem> findByUserIdAndFoodItemId(String userId, String foodItemId);
    void deleteByUserId(String userId);
    void deleteByUserIdAndFoodItemId(String userId, String foodItemId);
}