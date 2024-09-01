package it.cwk.notificationservice.service;

import it.cwk.notificationservice.model.MessageDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}

@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.info("Sending email to {}", messageDTO.getTo());

            // Create a new MimeMessage object.
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            // Load template of the email.
            Context context = new Context();
            context.setVariable("name", messageDTO.getToName());
            context.setVariable("content", messageDTO.getContent());
            String htmlContent = templateEngine.process("welcome-email", context);

            // Send the email.
            helper.setFrom(messageDTO.getFrom(), "Notification Service");
            helper.setTo(messageDTO.getTo());
            helper.setSubject(messageDTO.getSubject());
            helper.setText(htmlContent, true);
            javaMailSender.send(message);

            logger.info("Email sent to {}", messageDTO.getTo());
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("Error sending email: {}", e.getMessage());
        }
    }
}
