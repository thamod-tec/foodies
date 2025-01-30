package com.foodies.foodiesBackendImplementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.foodies.foodiesBackendImplementation.model.Notification;
import com.foodies.foodiesBackendImplementation.service.NotificationService;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    // Get all notifications (Admin)
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // Get notifications for a user
    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable String userId) {
        return notificationService.getNotificationsByUser(userId);
    }

     // Get notifications for a shop owner
     @GetMapping("/shop-owner/{shopOwnerId}")
     public List<Notification> getNotificationsByShopOwner(@PathVariable String shopOwnerId) {
         return notificationService.getNotificationsByShopOwner(shopOwnerId);
     }
 
     // Get unread notifications for a user
     @GetMapping("/user/{userId}/unread")
     public List<Notification> getUnreadNotificationsByUser(@PathVariable String userId) {
         return notificationService.getUnreadNotificationsByUser(userId);
     }
 
     // Create a new notification
     @PostMapping
     public Notification addNotification(@RequestBody Notification notification) {
         return notificationService.addNotification(notification);
     }
 
     // Mark a notification as read
     @PutMapping("/{id}/read")
     public Notification markNotificationAsRead(@PathVariable String id) {
         return notificationService.markNotificationAsRead(id);
     }
 
     // Mark all notifications as read for a user
     @PutMapping("/user/{userId}/read-all")
     public void markAllNotificationsAsRead(@PathVariable String userId) {
         notificationService.markAllNotificationsAsRead(userId);
     }
 
     // Delete a notification
     @DeleteMapping("/{id}")
     public void deleteNotification(@PathVariable String id) {
         notificationService.deleteNotification(id);
     }
 
     // Delete all notifications for a user
     @DeleteMapping("/user/{userId}")
     public void deleteAllUserNotifications(@PathVariable String userId) {
         notificationService.deleteAllUserNotifications(userId);
     }
 }

 