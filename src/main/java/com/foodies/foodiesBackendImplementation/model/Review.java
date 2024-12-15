package com.foodies.foodiesBackendImplementation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String userId; // User who wrote the review
    private String shopOwnerId; // Related shop (optional if for food item)
    private String foodItemId; // Related food item (optional if for shop)
    private String reviewText;
    private int rating; // Rating out of 5
    private String response; // Shop owner response (optional)
}