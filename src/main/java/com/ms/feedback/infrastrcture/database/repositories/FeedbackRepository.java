package com.ms.feedback.infrastrcture.database.repositories;

import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FeedbackRepository extends JpaRepository<FeedbackDocument, Long>, JpaSpecificationExecutor<FeedbackDocument> { }
