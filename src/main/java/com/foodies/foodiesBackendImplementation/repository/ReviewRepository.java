package com.foodies.foodiesBackendImplementation.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.foodies.foodiesBackendImplementation.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByShopOwnerId(String shopOwnerId);
    List<Review> findByFoodItemId(String foodItemId);
    List<Review> findByUserId(String userId);
}