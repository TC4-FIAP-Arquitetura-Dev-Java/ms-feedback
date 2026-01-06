package com.ms.feedback.application.gateway;

import com.ms.feedback.domain.model.FeedbackDomain;

public interface FeedbackSendEmailGateway {

    void sendEmail(FeedbackDomain feedback);
}
