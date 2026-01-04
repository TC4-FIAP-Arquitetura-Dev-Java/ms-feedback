package com.ms.feedback.infrastructure.database.mappers;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastructure.database.entities.FeedbackDocument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackDocumentMapperTest {

    @Test
    void shouldMapDomainToDocument() {
        FeedbackDomain domain = new FeedbackDomain();
        domain.setId("1");
        domain.setDescription("Muito bom");
        domain.setRating(10);
        domain.setUrgencyType(UrgencyTypeEnum.HIGH);

        FeedbackDocument document = FeedbackDocumentMapper.INSTANCE.toDocument(domain);

        assertEquals("Muito bom", document.getDescription());
        assertEquals(10, document.getRating());
        assertEquals(UrgencyTypeEnum.HIGH, document.getUrgencyType());
    }

    @Test
    void shouldMapDocumentToDomain() {
        FeedbackDocument document = new FeedbackDocument();
        document.setId(2L);
        document.setDescription("Regular");
        document.setRating(6);
        document.setUrgencyType(UrgencyTypeEnum.MEDIUM);

        FeedbackDomain domain = FeedbackDocumentMapper.INSTANCE.toDomain(document);

        assertEquals("2", domain.getId());
        assertEquals("Regular", domain.getDescription());
        assertEquals(6, domain.getRating());
        assertEquals(UrgencyTypeEnum.MEDIUM, domain.getUrgencyType());
    }
}