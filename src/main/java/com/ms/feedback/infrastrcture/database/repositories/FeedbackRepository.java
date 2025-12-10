package com.ms.feedback.infrastrcture.database.repositories;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedbackRepository extends MongoRepository<FeedbackDocument, String> {

    Optional<FeedbackDomain> findById(Long id);
}
