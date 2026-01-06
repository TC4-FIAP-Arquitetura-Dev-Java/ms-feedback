package com.ms.feedback.infrastructure.integration.client;

import com.ms.feedback.infrastructure.integration.configuration.FeignConfig;
import com.ms.feedback.infrastructure.integration.request.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notificationClient",
        url = "${feedback.email.url}",
        configuration = FeignConfig.class
)
public interface NotificationClient {

    @PostMapping(consumes = "application/json")
    void sendNotification(@RequestBody EmailRequest request);
}