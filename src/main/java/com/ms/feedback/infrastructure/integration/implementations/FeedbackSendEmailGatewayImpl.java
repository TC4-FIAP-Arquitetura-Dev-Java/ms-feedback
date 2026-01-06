package com.ms.feedback.infrastructure.integration.implementations;

import com.ms.feedback.application.gateway.FeedbackSendEmailGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastructure.integration.client.NotificationClient;
import com.ms.feedback.infrastructure.integration.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackSendEmailGatewayImpl implements FeedbackSendEmailGateway {

    private final NotificationClient notificationClient;

    @Override
    public void sendEmail(FeedbackDomain feedback) {
        EmailRequest request = EmailRequest.builder()
                .to(feedback.getEmail())
                .subject("Novo feedback recebido. Status: " + feedback.getUrgencyType().getDescription())
                .body("Descrição: " + feedback.getDescription() +
                        ". Nota: " + feedback.getRating())
                .build();

        notificationClient.sendNotification(request);
    }
}