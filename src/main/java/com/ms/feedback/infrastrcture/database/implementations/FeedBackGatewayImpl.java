package com.ms.feedback.infrastrcture.database.implementations;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import com.ms.feedback.infrastrcture.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastrcture.database.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedBackGatewayImpl implements FeedbackGateway {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Optional<FeedbackDomain> findById(String id) {
        return feedbackRepository.findById(id).map(FeedbackDocumentMapper.INSTANCE::toDomain);
    }

    @Override
    public List<FeedbackDomain> findAll() {
        return feedbackRepository.findAll().stream()
                .map(FeedbackDocumentMapper.INSTANCE::toDomain).collect(Collectors.toList());
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
