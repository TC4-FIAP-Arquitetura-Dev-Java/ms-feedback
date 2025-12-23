package com.ms.feedback.infrastrcture.database.implementations;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import com.ms.feedback.infrastrcture.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastrcture.database.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedBackGatewayImpl implements FeedbackGateway {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackDocumentMapper feedbackDocumentMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<FeedbackDomain> findById(String id) {
        return feedbackRepository.findById(id).map(feedbackDocumentMapper::toDomain);
    }

    @Override
    public void save(FeedbackDomain feedbackDomain) {
        FeedbackDocument feedbackDocument = feedbackDocumentMapper.toDocument(feedbackDomain);
        feedbackRepository.save(feedbackDocument);
    }

    @Override
    public void delete(FeedbackDomain feedbackDomain) {
        FeedbackDocument document = feedbackDocumentMapper.toDocument(feedbackDomain);
        feedbackRepository.delete(document);
    }

    @Override
    public Page<FeedbackDomain> findAll(FeedbackFilter filter, Pageable pageable) {
        Query mongoQuery = buildQuery(filter);
        long total = mongoTemplate.count(mongoQuery, FeedbackDocument.class);
        mongoQuery.with(pageable);

        List<FeedbackDomain> feedbackDomains = mongoTemplate
                .find(mongoQuery, FeedbackDocument.class)
                .stream()
                .map(feedbackDocumentMapper::toDomain)
                .toList();

        return new PageImpl<>(feedbackDomains, pageable, total);
    }

    private Query buildQuery(FeedbackFilter filter) {
        Query query = new Query();

        // Filtro por Descrição
        if (filter.descricao() != null && !filter.descricao().isBlank()) {
            query.addCriteria(Criteria.where("descricao").regex(filter.descricao().trim(), "i"));
        }

        // Filtro por Tipo Urgência
        if (filter.tipoUrgencia() != null && filter.tipoUrgencia().name() != null) {
            query.addCriteria(Criteria.where("tipoUrgencia").is(filter.tipoUrgencia().name()));
        }
        return query;
    }
}
