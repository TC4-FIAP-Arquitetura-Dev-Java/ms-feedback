package com.ms.feedback.infrastrcture.config.usecase;

import com.ms.feedback.application.usecase.implementation.UpdateFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateFeedbackConfig {

    @Bean
    public UpdateFeedbackUseCaseImpl updateFeedbackUseCase() {
        return null;
    }
}
