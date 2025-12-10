package com.ms.feedback.infrastrcture.database.implementations;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import com.ms.feedback.infrastrcture.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastrcture.database.repositories.FeedbackRepository;

import java.util.Optional;

public class FeedBackGatewayImpl implements FeedbackGateway {

    private final FeedbackRepository feedbackRepository;

    public FeedBackGatewayImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Optional<FeedbackDomain> findById(String id) {
        return feedbackRepository.findById(id).map(FeedbackDocumentMapper.INSTANCE::toDomain);
    }

    @Override
    public void save(FeedbackDomain feedbackDomain) {
        FeedbackDocument feedbackDocument = FeedbackDocumentMapper.INSTANCE.toDocument(feedbackDomain);
        feedbackRepository.save(feedbackDocument);
    }

    @Override
    public void delete(FeedbackDomain feedbackDomain) {
        FeedbackDocument document = FeedbackDocumentMapper.INSTANCE.toDocument(feedbackDomain);
        feedbackRepository.delete(document);
    }
}
