package com.ms.feedback.application.usecase;

import com.ms.feedback.domain.model.FeedbackDomain;

public interface CreateFeedbackUseCase {

    void create(FeedbackDomain feedback);
}
