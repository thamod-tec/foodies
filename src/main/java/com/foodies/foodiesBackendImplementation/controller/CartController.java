package com.foodies.foodiesBackendImplementation.controller;

import java.util.List;
import java.util.ArrayList;

public class CartController {
    private List<CartItem> items;
    private User currentUser;

    public CartController(User user) {
        this.currentUser = user;
        this.items = new ArrayList<>();
    }

    /**
     * Adds a new item to the cart
     * @param item The food item to be added
     * @return boolean indicating successful addition
     */
    public boolean addItem(CartItem item) {
        if (item == null) {
            return false;
        }
        return items.add(item);
    }

    /**
     * Updates quantity of an existing cart item
     * @param itemId ID of the item to update
     * @param newQuantity New quantity for the item
     * @return boolean indicating successful update
     */
    public boolean updateItemQuantity(String itemId, int newQuantity) {
        for (CartItem item : items) {
            if (item.getId().equals(itemId)) {
                if (newQuantity <= 0) {
                    return items.remove(item);
                }
                item.setQuantity(newQuantity);
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
        return items.removeIf(item -> item.getId().equals(itemId));
    }

    /**
     * Calculates total cart value
     * @return Total price of all items in cart
     */
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    /**
     * Prepares cart for order placement
     * @return Order object representing the current cart
     * @throws IllegalStateException if cart is empty
     */
    public Order placeOrder() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot place order with empty cart");
        }
        
        Order order = new Order(currentUser, new ArrayList<>(items));
        clearCart();
        return order;
    }

    /**
     * Clears all items from the cart
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * Gets current items in the cart
     * @return List of cart items
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
}
// Supporting classes for reference
class CartItem {
    private String id;
    private String name;
    private double price;
    private int quantity;

    // Constructor, getters, and setters
    public String getId() { return id; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}

class User {
    private String userId;
    private String name;
    // Additional user details
}

class Order {
    private User user;
    private List<CartItem> orderedItems;
    private double totalPrice;

    public Order(User user, List<CartItem> items) {
        this.user = user;
        this.orderedItems = items;
        this.totalPrice = calculateOrderTotal(items);
    }

    private double calculateOrderTotal(List<CartItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}

