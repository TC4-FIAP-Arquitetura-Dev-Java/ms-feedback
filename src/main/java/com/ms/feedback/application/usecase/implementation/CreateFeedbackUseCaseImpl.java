package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.CreateFeedbackUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.rules.RequiredFieldsRules;

public class CreateFeedbackUseCaseImpl implements CreateFeedbackUseCase {

    private final FeedbackGateway feedbackGateway;

    public CreateFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public void create(FeedbackDomain feedback) {
        RequiredFieldsRules.checkRequiredFields(feedback);
        feedbackGateway.save(feedback);
    }
}
