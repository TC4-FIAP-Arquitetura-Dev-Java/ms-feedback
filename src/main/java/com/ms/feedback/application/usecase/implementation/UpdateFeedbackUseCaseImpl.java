package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.UpdateFeedbackUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;

public class UpdateFeedbackUseCaseImpl implements UpdateFeedbackUseCase {

    private final FeedbackGateway  feedbackGateway;

    public UpdateFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public FeedbackDomain update(String id, FeedbackDomain feedback) {
        return null;
    }
}
