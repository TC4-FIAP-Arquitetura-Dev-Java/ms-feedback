package com.ms.feedback.application.usecase;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.entrypoint.controllers.filter.FeedbackFilter;

import java.util.List;

public interface ListFeedbackUseCase {

    List<FeedbackDomain> findAll(FeedbackFilter filter);
}
