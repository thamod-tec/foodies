package com.foodies.foodiesBackendImplementation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodies.foodiesBackendImplementation.model.Review;
import com.foodies.foodiesBackendImplementation.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByShopOwner(String shopOwnerId) {
        return reviewRepository.findByShopOwnerId(shopOwnerId);
    }

    public List<Review> getReviewsByFoodItem(String foodItemId) {
        return reviewRepository.findByFoodItemId(foodItemId);
    }

    public List<Review> getReviewsByUser(String userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review respondToReview(String id, String response) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setResponse(response);
        return reviewRepository.save(review);
    }

    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }
}