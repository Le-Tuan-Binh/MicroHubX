package com.example.main.client;

import com.example.main.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:9083", path = "/send-notification")
public interface NotificationService {
    @PostMapping
    void sendNotification(@RequestBody MessageDTO messageDTO);
}
