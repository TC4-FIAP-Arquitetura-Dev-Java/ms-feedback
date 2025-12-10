package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.CreateFeedbackUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;

public class CreateFeedbackUseCaseImpl implements CreateFeedbackUseCase {

    private final FeedbackGateway  feedbackGateway;

    public CreateFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public FeedbackDomain create(FeedbackDomain feedback) {
        return null;
    }
}
