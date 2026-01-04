package com.ms.feedback.infrastructure.database.mappers;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastructure.database.entities.FeedbackDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackDocumentMapper {

    FeedbackDocumentMapper INSTANCE = Mappers.getMapper(FeedbackDocumentMapper.class);

    @Mapping(target = "id", expression = "java(document.getId() != null ? String.valueOf(document.getId()) : null)")
    @Mapping(target = "sentDate", expression = "java(toOffsetDateTime(document.getSentDate()))")
    FeedbackDomain toDomain(FeedbackDocument document);

    @Mapping(target = "id", expression = "java(toLongId(domain.getId()))")
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

    default Long toLongId(String id) {
        if(id != null && !id.isEmpty()) {
            try {
                return Long.parseLong(id);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
