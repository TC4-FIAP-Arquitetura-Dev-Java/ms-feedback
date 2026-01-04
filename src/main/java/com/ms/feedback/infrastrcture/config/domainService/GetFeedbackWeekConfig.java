package com.ms.feedback.infrastrcture.config.domainService;

import com.ms.feedback.application.gateway.FeedbackReportWeekGateway;
import com.ms.feedback.domain.domainService.impl.GetFeedbackWeekDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetFeedbackWeekConfig {

    @Bean
    public GetFeedbackWeekDomainServiceImpl getFeedbackWeekDomainService(FeedbackReportWeekGateway feedbackReportWeekGateway) {
        return new GetFeedbackWeekDomainServiceImpl(feedbackReportWeekGateway);
    }
}
