package com.ms.feedback.infrastructure.config.usecase;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.implementation.UpdateFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateFeedbackConfig {

    @Bean
    public UpdateFeedbackUseCaseImpl updateFeedbackUseCase(FeedbackGateway feedbackGateway) {
        return new UpdateFeedbackUseCaseImpl(feedbackGateway);
    }
}
