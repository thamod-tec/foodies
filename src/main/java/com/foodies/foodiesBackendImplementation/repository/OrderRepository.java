package com.foodies.foodiesBackendImplementation.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.foodies.foodiesBackendImplementation.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);
    List<Order> findByShopOwnerId(String shopOwnerId);
    List<Order> findByOrderStatus(String orderStatus);
}