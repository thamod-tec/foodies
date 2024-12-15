package com.foodies.foodiesBackendImplementation.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private String shopOwnerId;
    private List<String> foodItemIds;
    private double totalPrice;
    private int quantity;
    private String orderStatus; // Pending, Preparing, Delivered
    private String deliveryAddress;
    private Date orderDate;
    private String contactNumber;
}