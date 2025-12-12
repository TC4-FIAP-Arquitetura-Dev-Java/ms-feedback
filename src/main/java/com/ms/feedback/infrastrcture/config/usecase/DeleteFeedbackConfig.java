package com.ms.feedback.infrastrcture.config.usecase;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.implementation.DeleteFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteFeedbackConfig {

    @Bean
    public DeleteFeedbackUseCaseImpl deleteFeedbackUseCase(FeedbackGateway feedbackGateway) {
        return new DeleteFeedbackUseCaseImpl(feedbackGateway);
    }
}
