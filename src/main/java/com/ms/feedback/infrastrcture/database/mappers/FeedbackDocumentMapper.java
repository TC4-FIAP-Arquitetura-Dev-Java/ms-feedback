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

    @Mapping(target = "sentDate", expression = "java(toOffsetDateTime(document.getSentDate()))")
    FeedbackDomain toDomain(FeedbackDocument document);

    @Mapping(target = "sentDate", expression = "java(toInstant(domain.getSentDate()))")
    FeedbackDocument toDocument(FeedbackDomain domain);

    default OffsetDateTime toOffsetDateTime(Instant instant) {
        if(instant != null) {
            return instant.atOffset(ZoneOffset.UTC);
        }
        return null;
    }

    default Instant toInstant(OffsetDateTime offsetDateTime) {
        if(offsetDateTime != null) {
            return offsetDateTime.toInstant();
        }
        return null;
    }
}
