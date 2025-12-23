package com.ms.feedback.infrastrcture.database.repositories;

import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<FeedbackDocument, String> { }
