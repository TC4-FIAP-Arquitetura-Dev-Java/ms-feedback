package com.ms.feedback.infrastrcture.database.mappers;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackDocumentMapper {

    FeedbackDocumentMapper INSTANCE = Mappers.getMapper(FeedbackDocumentMapper.class);

//    @Mapping(target = "dataEnvio", expression = "java(document.getDataEnvio() != null ? document.getDataEnvio().atOffset(java.time.ZoneOffset.UTC) : null)")
    FeedbackDomain toDomain(FeedbackDocument document);

//    @Mapping(target = "dataEnvio", expression = "java(domain.getDataEnvio() != null ? domain.getDataEnvio().toInstant() : null)")
    FeedbackDocument toDocument(FeedbackDomain domain);
}
