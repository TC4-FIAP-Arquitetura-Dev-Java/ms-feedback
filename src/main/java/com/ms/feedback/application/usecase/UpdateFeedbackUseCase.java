package com.ms.feedback.application.usecase;

import com.ms.feedback.domain.model.FeedbackDomain;

public interface UpdateFeedbackUseCase {

    FeedbackDomain update(String id, FeedbackDomain feedback);
}
