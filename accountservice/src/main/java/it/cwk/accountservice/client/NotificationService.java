package it.cwk.accountservice.client;

import it.cwk.accountservice.model.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8083/api/v1/notification")
public interface NotificationService {

    @PostMapping("/send-email")
    void sendNotification(@RequestBody MessageDTO messageDTO);

}
