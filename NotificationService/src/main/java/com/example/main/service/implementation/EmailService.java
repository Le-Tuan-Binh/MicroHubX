package com.example.main.service.implementation;

import com.example.main.dto.MessageDTO;
import com.example.main.service.contract.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService implements IEmailService {

    private final SpringTemplateEngine templateEngine;

    private final JavaMailSender mailSender;
    private final JavaMailSender javaMailSender;

    public EmailService(SpringTemplateEngine templateEngine, JavaMailSender mailSender, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(MessageDTO messageDTO) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("name", messageDTO.getToName());
        context.setVariable("content", messageDTO.getContent());

        String html = templateEngine.process("email", context);

        helper.setTo(messageDTO.getTo());
        helper.setText(messageDTO.getContent());
        helper.setSubject(messageDTO.getSubject());
        helper.setFrom(messageDTO.getFrom());

        javaMailSender.send(message);
    }
}
