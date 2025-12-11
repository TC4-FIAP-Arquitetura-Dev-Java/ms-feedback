package com.ms.feedback.infrastrcture.config.usecase;

import com.ms.feedback.application.usecase.implementation.CreateFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateFeedbackConfig {

    @Bean
    public CreateFeedbackUseCaseImpl createFeedbackUseCase() {
        return null;
    }
}
