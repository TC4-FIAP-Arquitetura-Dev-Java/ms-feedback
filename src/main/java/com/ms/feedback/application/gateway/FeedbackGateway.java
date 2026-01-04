package com.ms.feedback.application.gateway;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FeedbackGateway {

    Optional<FeedbackDomain> findById(String id);

    Page<FeedbackDomain> findAll(FeedbackFilter filter, Pageable pageable);

    void save(FeedbackDomain feedbackDomain);

    void delete(FeedbackDomain feedbackDomain);
}
