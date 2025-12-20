package com.ms.feedback.application.gateway;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.domain.model.FeedbackDomain;

import java.util.List;
import java.util.Optional;

public interface FeedbackGateway {

    Optional<FeedbackDomain> findById(String id);

    List<FeedbackDomain> findAll(FeedbackFilter filter);

    void save(FeedbackDomain feedbackDomain);

    void delete(FeedbackDomain feedbackDomain);
}
