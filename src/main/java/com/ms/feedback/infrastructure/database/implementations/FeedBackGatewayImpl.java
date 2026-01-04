package com.ms.feedback.infrastructure.database.implementations;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastructure.database.entities.FeedbackDocument;
import com.ms.feedback.infrastructure.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastructure.database.repositories.FeedbackRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedBackGatewayImpl implements FeedbackGateway {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackDocumentMapper feedbackDocumentMapper;

    @Override
    public Optional<FeedbackDomain> findById(String id) {
        try {
            Long longId = Long.parseLong(id);
            return feedbackRepository.findById(longId).map(feedbackDocumentMapper::toDomain);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(FeedbackDomain feedbackDomain) {
        FeedbackDocument feedbackDocument = feedbackDocumentMapper.toDocument(feedbackDomain);
        FeedbackDocument saved = feedbackRepository.save(feedbackDocument);
        feedbackDomain.setId(String.valueOf(saved.getId()));
    }

    @Override
    public void delete(FeedbackDomain feedbackDomain) {
        try {
            Long longId = Long.parseLong(feedbackDomain.getId());
            feedbackRepository.deleteById(longId);
        } catch (NumberFormatException e) {
            // If ID is not a valid Long, try to find and delete by entity
            FeedbackDocument document = feedbackDocumentMapper.toDocument(feedbackDomain);
            if (document.getId() != null) {
                feedbackRepository.delete(document);
            }
        }
    }

    @Override
    public Page<FeedbackDomain> findAll(FeedbackFilter filter, Pageable pageable) {
        Specification<FeedbackDocument> spec = buildSpecification(filter);
        Page<FeedbackDocument> page = feedbackRepository.findAll(spec, pageable);
        return page.map(feedbackDocumentMapper::toDomain);
    }

    private Specification<FeedbackDocument> buildSpecification(FeedbackFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por Descrição
            if (filter.description() != null && !filter.description().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("description")),
                    "%" + filter.description().trim().toLowerCase() + "%"
                ));
            }

            // Filtro por Tipo Urgência
            if (filter.urgencyType() != null) {
                predicates.add(cb.equal(root.get("urgencyType"), filter.urgencyType()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
