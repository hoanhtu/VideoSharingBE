package com.tuho.YouTubeSharingApp.service;


import com.tuho.YouTubeSharingApp.entity.Notification;
import com.tuho.YouTubeSharingApp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate template;

    public void notifyUsers(String message, Long userId) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);
        notificationRepository.save(notification);
        template.convertAndSend("/topic/notifications", message);
    }
}
