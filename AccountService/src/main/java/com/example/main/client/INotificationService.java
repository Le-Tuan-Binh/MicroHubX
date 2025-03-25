package com.example.main.client;

import com.example.main.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", path = "/send-notification", fallback = NotificationService.class)
public interface INotificationService {
    @PostMapping
    void sendNotification(@RequestBody MessageDTO messageDTO);
}

@Component
class NotificationService implements INotificationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendNotification(MessageDTO messageDTO) {
        // Fallback
        logger.error("Notification service is slow");
    }
}