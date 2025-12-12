package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.ListFeedbackUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;

import java.util.List;

public class ListFeedbackUseCaseImpl implements ListFeedbackUseCase {

    private final FeedbackGateway feedbackGateway;

    public ListFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public List<FeedbackDomain> findAll() {
        return feedbackGateway.findAll();
    }
}
