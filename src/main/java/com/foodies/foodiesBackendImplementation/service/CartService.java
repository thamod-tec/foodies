package com.foodies.foodiesBackendImplementation.service;

import com.foodies.foodiesBackendImplementation.model.CartItem;
import com.foodies.foodiesBackendImplementation.model.FoodItem;
import com.foodies.foodiesBackendImplementation.model.Order;
import com.foodies.foodiesBackendImplementation.repository.CartRepository;
import com.foodies.foodiesBackendImplementation.repository.FoodItemRepository;
import com.foodies.foodiesBackendImplementation.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private FoodItemRepository foodItemRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Adds an item to the cart
     * @param userId User ID
     * @param foodItemId Food item ID
     * @param quantity Quantity to add
     * @return The added cart item
     */
    public CartItem addToCart(String userId, String foodItemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        
        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartRepository.findByUserIdAndFoodItemId(userId, foodItemId);
        
        if (existingItem.isPresent()) {
            // Update quantity if item already exists
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            return cartRepository.save(item);
        } else {
            // Create new cart item
            Optional<FoodItem> foodItemOpt = foodItemRepository.findById(foodItemId);
            if (foodItemOpt.isEmpty()) {
                throw new IllegalArgumentException("Food item not found");
            }
            
            FoodItem foodItem = foodItemOpt.get();
            CartItem cartItem = new CartItem();
            cartItem.setId(UUID.randomUUID().toString());
            cartItem.setUserId(userId);
            cartItem.setFoodItemId(foodItemId);
            cartItem.setName(foodItem.getName());
            cartItem.setDescription(foodItem.getDescription());
            cartItem.setPrice(foodItem.getPrice());
            cartItem.setQuantity(quantity);
            cartItem.setShopOwnerId(foodItem.getShopOwnerId());
            
            return cartRepository.save(cartItem);
        }
    }

    /**
     * Updates quantity of an item in the cart
     * @param userId User ID
     * @param cartItemId Cart item ID
     * @param newQuantity New quantity
     * @return Updated cart item
     */
    public CartItem updateCartItemQuantity(String userId, String cartItemId, int newQuantity) {
        CartItem cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        
        // Verify ownership
        if (!cartItem.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Cart item does not belong to user");
        }
        
        if (newQuantity <= 0) {
            cartRepository.deleteById(cartItemId);
            return null;
        }
        
        cartItem.setQuantity(newQuantity);
        return cartRepository.save(cartItem);
    }

    /**
     * Removes an item from the cart
     * @param userId User ID
     * @param cartItemId Cart item ID
     */
    public void removeFromCart(String userId, String cartItemId) {
        CartItem cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        
        // Verify ownership
        if (!cartItem.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Cart item does not belong to user");
        }
        
        cartRepository.deleteById(cartItemId);
    }

    /**
     * Gets all items in a user's cart
     * @param userId User ID
     * @return List of cart items
     */
    public List<CartItem> getCartItems(String userId) {
        return cartRepository.findByUserId(userId);
    }

    /**
     * Calculates the total price of items in the cart
     * @param userId User ID
     * @return Total price
     */
    public double calculateCartTotal(String userId) {
        List<CartItem> cartItems = cartRepository.findByUserId(userId);
        return cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    /**
     * Clears all items from the cart
     * @param userId User ID
     */
    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }

    /**
     * Places an order from the cart items
     * @param userId User ID
     * @param deliveryAddress Delivery address
     * @param contactNumber Contact number
     * @return The created order
     */
    @Transactional
    public Order placeOrder(String userId, String deliveryAddress, String contactNumber) {
        List<CartItem> cartItems = cartRepository.findByUserId(userId);
        
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cannot place order with empty cart");
        }
        
        // Create order
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(userId);
        
        // Assuming all items are from the same shop for simplicity
        // In a real system, you might need to handle multiple shop owners
        order.setShopOwnerId(cartItems.get(0).getShopOwnerId());
        
        // Store food item IDs
        List<String> foodItemIds = new ArrayList<>();
        for (CartItem item : cartItems) {
            foodItemIds.add(item.getFoodItemId());
        }
        order.setFoodItemIds(foodItemIds);
        
        // Calculate total price
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);
        
        // Set order metadata
        order.setOrderStatus("Pending");
        order.setDeliveryAddress(deliveryAddress);
        order.setContactNumber(contactNumber);
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        
        // Save order
        Order savedOrder = orderRepository.save(order);
        
        // Clear cart after order is placed
        clearCart(userId);
        
        return savedOrder;
    }
}