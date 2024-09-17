package com.tuho.YouTubeSharingApp.repository;

import com.tuho.YouTubeSharingApp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
