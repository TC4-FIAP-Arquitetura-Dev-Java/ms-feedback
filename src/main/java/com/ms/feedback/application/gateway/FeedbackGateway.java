package com.ms.feedback.application.gateway;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.entrypoint.controllers.filter.FeedbackFilter;

import java.util.List;
import java.util.Optional;

public interface FeedbackGateway {

    Optional<FeedbackDomain> findById(String id);

    List<FeedbackDomain> findAll(FeedbackFilter filter);

    void save(FeedbackDomain feedbackDomain);

    void delete(FeedbackDomain feedbackDomain);
}
