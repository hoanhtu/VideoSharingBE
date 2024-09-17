package com.tuho.YouTubeSharingApp.controller;

import com.tuho.YouTubeSharingApp.entity.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public Notification send(Notification notification) {
        return notification;
    }
}
