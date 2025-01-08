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