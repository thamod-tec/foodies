package com.foodies.foodiesBackendImplementation.service;

import com.foodies.foodiesBackendImplementation.model.Notification;
import com.foodies.foodiesBackendImplementation.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsByUser(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> getNotificationsByShopOwner(String shopOwnerId) {
        return notificationRepository.findByShopOwnerId(shopOwnerId);
    }

    public List<Notification> getUnreadNotificationsByUser(String userId) {
        return notificationRepository.findByUserIdAndRead(userId, false);
    }

    