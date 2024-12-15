package com.foodies.foodiesBackendImplementation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodies.foodiesBackendImplementation.model.FoodItem;
import com.foodies.foodiesBackendImplementation.repository.FoodItemRepository;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public List<FoodItem> getFoodItemsByShopOwner(String shopOwnerId) {
        return foodItemRepository.findByShopOwnerId(shopOwnerId);
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public FoodItem updateFoodItem(String id, FoodItem updatedFoodItem) {
        updatedFoodItem.setId(id);
        return foodItemRepository.save(updatedFoodItem);
    }

    public void deleteFoodItem(String id) {
        foodItemRepository.deleteById(id);
    }
}