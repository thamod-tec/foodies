package com.foodies.foodiesBackendImplementation.repository;

import com.foodies.foodiesBackendImplementation.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByUserId(String userId);
    List<Notification> findByShopOwnerId(String shopOwnerId);
    List<Notification> findByUserIdAndRead(String userId, boolean read);
    void deleteByUserId(String userId);
}
