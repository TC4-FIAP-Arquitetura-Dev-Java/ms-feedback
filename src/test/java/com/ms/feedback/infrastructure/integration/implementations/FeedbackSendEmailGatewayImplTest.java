package com.ms.feedback.infrastructure.integration.implementations;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastructure.integration.client.NotificationClient;
import com.ms.feedback.infrastructure.integration.request.EmailRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeedbackSendEmailGatewayImplTest {

    @Mock
    private NotificationClient notificationClient;

    @InjectMocks
    private FeedbackSendEmailGatewayImpl gateway;

    @Test
    void shouldSendEmailWithCorrectData() {
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setEmail("teste@email.com");
        feedback.setDescription("Excelente curso");
        feedback.setRating(5);
        feedback.setUrgencyType(UrgencyTypeEnum.HIGH);

        gateway.sendEmail(feedback);

        ArgumentCaptor<EmailRequest> captor =
                ArgumentCaptor.forClass(EmailRequest.class);

        verify(notificationClient).sendNotification(captor.capture());

        EmailRequest request = captor.getValue();

        assertThat(request.getTo()).isEqualTo("teste@email.com");
        assertThat(request.getSubject())
                .contains("Novo feedback recebido")
                .contains("High");

        assertThat(request.getBody())
                .contains("Excelente curso")
                .contains("5");
    }
}