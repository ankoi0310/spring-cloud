package it.cwk.accountservice.client;

import it.cwk.accountservice.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8083/api/v1/notification", fallback = NotificationServiceImpl.class)
public interface NotificationService {

    @PostMapping("/send-email")
    void sendNotification(@RequestBody MessageDTO messageDTO);

}

@Component
class NotificationServiceImpl implements NotificationService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void sendNotification(MessageDTO messageDTO) {
        logger.error("Notification service is not available");
    }

}
