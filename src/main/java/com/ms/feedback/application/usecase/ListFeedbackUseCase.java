package com.ms.feedback.application.usecase;

import com.ms.feedback.domain.model.FeedbackDomain;

import java.util.List;

public interface ListFeedbackUseCase {

    List<FeedbackDomain> findAll();
}
