package com.ms.feedback.infrastructure.config.usecase;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.implementation.ListFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListFeedbackUseCaseConfig {

    @Bean
    public ListFeedbackUseCaseImpl listFeedbackUseCase(FeedbackGateway feedbackGateway) {
        return new ListFeedbackUseCaseImpl(feedbackGateway);
    }
}
