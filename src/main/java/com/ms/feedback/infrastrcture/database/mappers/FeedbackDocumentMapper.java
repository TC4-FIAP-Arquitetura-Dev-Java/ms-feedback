package com.ms.feedback.infrastrcture.database.mappers;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastrcture.database.entities.FeedbackDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackDocumentMapper {

    @Mapping(target = "dataEnvio", expression = "java(toOffsetDateTime(document.getDataEnvio()))")
    FeedbackDomain toDomain(FeedbackDocument document);

    @Mapping(target = "dataEnvio", expression = "java(toInstant(domain.getDataEnvio()))")
    FeedbackDocument toDocument(FeedbackDomain domain);

    default OffsetDateTime toOffsetDateTime(Instant instant) {
        return instant != null
                ? instant.atOffset(ZoneOffset.UTC)
                : null;
    }

    default Instant toInstant(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null
                ? offsetDateTime.toInstant()
                : null;
    }
}
