package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.ListFeedbackUseCase;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListFeedbackUseCaseImpl implements ListFeedbackUseCase {

    private final FeedbackGateway feedbackGateway;

    public ListFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public Page<FeedbackDomain> findAll(FeedbackFilter filter, Pageable pageable) {
        return feedbackGateway.findAll(filter, pageable);
    }
}
