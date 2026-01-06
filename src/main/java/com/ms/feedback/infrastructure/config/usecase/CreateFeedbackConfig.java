package com.ms.feedback.infrastructure.config.usecase;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.gateway.FeedbackSendEmailGateway;
import com.ms.feedback.application.usecase.implementation.CreateFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateFeedbackConfig {

    @Bean
    public CreateFeedbackUseCaseImpl createFeedbackUseCase(FeedbackGateway feedbackGateway,
                                                           FeedbackSendEmailGateway feedbackSendEmailGateway) {
        return new CreateFeedbackUseCaseImpl(feedbackGateway, feedbackSendEmailGateway);
    }
}
