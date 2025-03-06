package com.foodies.foodiesBackendImplementation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cart_items")
public class CartItem {
    @Id
    private String id;
    private String userId;
    private String foodItemId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String shopOwnerId;
}