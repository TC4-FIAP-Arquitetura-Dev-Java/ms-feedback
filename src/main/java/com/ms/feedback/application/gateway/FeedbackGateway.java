package com.ms.feedback.application.gateway;

import com.ms.feedback.domain.model.FeedbackDomain;

import java.util.Optional;

public interface FeedbackGateway {

    Optional<FeedbackDomain> findById(String id);

    void save(FeedbackDomain feedbackDomain);

    void delete(FeedbackDomain feedbackDomain);
}
