package com.ms.feedback.infrastrcture.config.usecase;

import com.ms.feedback.application.usecase.implementation.DeleteFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteFeedbackConfig {

    @Bean
    public DeleteFeedbackUseCaseImpl deleteFeedbackUseCase() {
        return null;
    }
}
