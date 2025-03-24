package com.example.main.controller;

import com.example.main.dto.MessageDTO;
import com.example.main.service.implementation.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-notification")
public class NotificationController {
    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendNotification(@RequestBody MessageDTO messageDTO) throws MessagingException {
        emailService.sendEmail(messageDTO);
    }
}
