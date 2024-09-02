package it.cwk.notificationservice.controller;

import it.cwk.notificationservice.model.MessageDTO;
import it.cwk.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final EmailService emailService;

    @PostMapping("/send-email")
    public void sendNotification(@RequestBody MessageDTO messageDTO) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailService.sendEmail(messageDTO);
    }
}
