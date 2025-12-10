package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.DeleteFeedbackUseCase;

public class DeleteFeedbackUseCaseImpl implements DeleteFeedbackUseCase {

    private final FeedbackGateway  feedbackGateway;

    public DeleteFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public void delete(String id) {

    }
}
