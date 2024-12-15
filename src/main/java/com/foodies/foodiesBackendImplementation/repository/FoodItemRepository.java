package com.foodies.foodiesBackendImplementation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.foodies.foodiesBackendImplementation.model.FoodItem;

public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    List<FoodItem> findByShopOwnerId(String shopOwnerId);
}