package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.ListFeedbackUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.entrypoint.controllers.filter.FeedbackFilter;

import java.util.List;

public class ListFeedbackUseCaseImpl implements ListFeedbackUseCase {

    private final FeedbackGateway feedbackGateway;

    public ListFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public List<FeedbackDomain> findAll(FeedbackFilter filter) {
        return feedbackGateway.findAll(filter);
    }
}
