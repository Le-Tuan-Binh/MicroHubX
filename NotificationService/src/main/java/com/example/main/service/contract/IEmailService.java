package com.example.main.service.contract;

import com.example.main.dto.MessageDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendEmail(MessageDTO messageDTO) throws MessagingException;
}
