package com.ms.feedback.infrastructure.config.usecase;

import com.ms.feedback.application.usecase.implementation.GetFeedbackReportUseCaseImpl;
import com.ms.feedback.domain.domainService.GetFeedbackWeekDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetFeedbackReportConfig {

    @Bean
    public GetFeedbackReportUseCaseImpl getFeedbackReportUseCase(GetFeedbackWeekDomainService getFeedbackWeekDomainService) {
        return new GetFeedbackReportUseCaseImpl(getFeedbackWeekDomainService);
    }
}
