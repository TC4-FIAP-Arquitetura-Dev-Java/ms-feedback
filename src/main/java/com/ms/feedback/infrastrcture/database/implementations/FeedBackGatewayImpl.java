package com.ms.feedback.infrastrcture.database.implementations;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.entrypoint.controllers.filter.FeedbackFilter;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import com.ms.feedback.infrastrcture.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastrcture.database.repositories.FeedbackRepository;
import com.ms.loginDomain.gen.model.TipoUrgenciaEnumDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    public List<FeedbackDomain> findAll(FeedbackFilter filter) {
        Query mongoQuery = buildQuery(filter);

        return mongoTemplate
                .find(mongoQuery, FeedbackDocument.class)
                .stream()
                .map(feedbackDocumentMapper::toDomain)
                .toList();
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

    private Query buildQuery(FeedbackFilter filter) {
        Query query = new Query();

        // Filtro por Descrição
        if (filter.descricao() != null && !filter.descricao().isBlank()) {
            query.addCriteria(Criteria.where("descricao").regex(filter.descricao().trim(), "i"));
        }

        // Filtro por Tipo Urgência
        if (filter.tipoUrgencia().getDescricao() != null) {
            String tipoUrgencia = filter.tipoUrgencia().getDescricao();
            query.addCriteria(Criteria.where("tipoUrgencia").is(tipoUrgencia));
        }

        //TODO: Terminar o filter e validação

        // Paginação
        if (filter.limit() != null && filter.limit() > 0) {
            if (filter.offset() != null) {
                int offset = filter.offset();

                if (offset < 0) {
                    offset = 0;
                }
                query.skip(offset).limit(filter.limit());
            }
        }
        return query;
    }
}
