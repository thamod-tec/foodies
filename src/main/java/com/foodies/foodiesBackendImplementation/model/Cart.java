package com.foodies.foodiesBackendImplementation.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user's shopping cart in the food ordering system.
 * A cart contains multiple cart items and belongs to a specific user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    
    // The user who owns this cart
    private String userId;
    
    // List of items in the cart
    private List<CartItem> items = new ArrayList<>();
    
    // Total price of all items in the cart
    private double totalPrice;
    
    // Timestamp when the cart was last updated
    private long lastUpdated;
    
    /**
     * Adds a new item to the cart
     * @param item The food item to be added
     * @return boolean indicating successful addition
     */
    public boolean addItem(CartItem item) {
        if (item == null) {
            return false;
        }
        
        // Check if item already exists in cart
        for (CartItem existingItem : items) {
            if (existingItem.getFoodItemId().equals(item.getFoodItemId())) {
                // Update quantity if item already exists
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                updateTotalPrice();
                this.lastUpdated = System.currentTimeMillis();
                return true;
            }
        }
        
        // Add new item if not found
        boolean added = items.add(item);
        if (added) {
            updateTotalPrice();
            this.lastUpdated = System.currentTimeMillis();
        }
        return added;
    }
    
    /**
     * Updates quantity of an existing cart item
     * @param itemId ID of the item to update
     * @param newQuantity New quantity for the item
     * @return boolean indicating successful update
     */
    public boolean updateItemQuantity(String itemId, int newQuantity) {
        if (newQuantity <= 0) {
            return removeItem(itemId);
        }
        
        for (CartItem item : items) {
            if (item.getId().equals(itemId)) {
                item.setQuantity(newQuantity);
                updateTotalPrice();
                this.lastUpdated = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Removes an item from the cart
     * @param itemId ID of the item to remove
     * @return boolean indicating successful removal
     */
    public boolean removeItem(String itemId) {
        boolean removed = items.removeIf(item -> item.getId().equals(itemId));
        if (removed) {
            updateTotalPrice();
            this.lastUpdated = System.currentTimeMillis();
        }
        return removed;
    }
    
    /**
     * Clears all items from the cart
     */
    public void clearCart() {
        items.clear();
        this.totalPrice = 0;
        this.lastUpdated = System.currentTimeMillis();
    }
    
    /**
     * Updates the total price based on current items
     */
    private void updateTotalPrice() {
        this.totalPrice = items.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
    }
    
    /**
     * Checks if the cart is empty
     * @return true if cart has no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    /**
     * Gets the number of different items in the cart
     * @return item count
     */
    public int getItemCount() {
        return items.size();
    }
    
    /**
     * Gets the total quantity of all items
     * @return total quantity
     */
    public int getTotalQuantity() {
        return items.stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
    }
    
    /**
     * Checks if the cart contains items from multiple shops
     * @return true if items are from multiple shops
     */
    public boolean hasMultipleShops() {
        if (items.isEmpty()) {
            return false;
        }
        
        String firstShopId = items.get(0).getShopOwnerId();
        return items.stream()
            .anyMatch(item -> !item.getShopOwnerId().equals(firstShopId));
    }
}