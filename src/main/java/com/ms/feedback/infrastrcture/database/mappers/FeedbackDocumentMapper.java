package com.ms.feedback.infrastrcture.database.mappers;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackDocumentMapper {

    FeedbackDocumentMapper INSTANCE = Mappers.getMapper(FeedbackDocumentMapper.class);

    @Mapping(target = "dataCriacao", expression = "java(feedBackDocument.getDataEnvio() != null ? feedBackDocument.getDataEnvio().atOffset(java.time.ZoneOffset.UTC) : null)")
    FeedbackDomain toDomain(FeedbackDocument document);

    @Mapping(target = "dataCriacao", expression = "java(feedBackDocument.getDataEnvio() != null ? feedBackDocument.getDataEnvio().toInstant() : null)")
    FeedbackDocument toDocument(FeedbackDomain domain);

}
