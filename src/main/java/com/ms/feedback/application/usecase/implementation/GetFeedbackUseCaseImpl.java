package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.GetFeedbackByIdUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;

public class GetFeedbackUseCaseImpl implements GetFeedbackByIdUseCase {

    private final FeedbackGateway  feedbackGateway;

    public GetFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public FeedbackDomain getById(String id) {
        return null;
    }
}
