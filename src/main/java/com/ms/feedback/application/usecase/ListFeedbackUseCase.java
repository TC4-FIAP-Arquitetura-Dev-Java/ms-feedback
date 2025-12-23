package com.ms.feedback.application.usecase;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListFeedbackUseCase {

    Page<FeedbackDomain> findAll(FeedbackFilter filter, Pageable pageable);
}
