package com.ms.feedback.infrastrcture.config.usecase;

import com.ms.feedback.application.usecase.implementation.GetFeedbackUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetFeedbackConfig {

    @Bean
    public GetFeedbackUseCaseImpl getFeedbackUseCase() {
        return null;
    }
}
