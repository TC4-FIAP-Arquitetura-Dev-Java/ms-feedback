package com.ms.feedback.infrastructure.database.implementations;

import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.infrastructure.database.entities.FeedbackDocument;
import com.ms.feedback.infrastructure.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastructure.database.repositories.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedBackGatewayImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private FeedbackDocumentMapper feedbackDocumentMapper;

    @InjectMocks
    private FeedBackGatewayImpl gateway;

    @Test
    void findById_shouldReturnDomain_whenIdIsValid() {
        String id = "1";
        FeedbackDocument document = new FeedbackDocument();
        document.setId(1L);

        FeedbackDomain domain = new FeedbackDomain();
        domain.setId("1");

        when(feedbackRepository.findById(1L))
                .thenReturn(Optional.of(document));
        when(feedbackDocumentMapper.toDomain(document))
                .thenReturn(domain);

        Optional<FeedbackDomain> result = gateway.findById(id);

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
        verify(feedbackRepository).findById(1L);
        verify(feedbackDocumentMapper).toDomain(document);
    }

    @Test
    void findById_shouldReturnEmpty_whenIdIsInvalid() {
        Optional<FeedbackDomain> result = gateway.findById("abc");

        assertTrue(result.isEmpty());
        verifyNoInteractions(feedbackRepository);
        verifyNoInteractions(feedbackDocumentMapper);
    }

    @Test
    void save_shouldPersistAndSetIdBackToDomain() {
        FeedbackDomain domain = new FeedbackDomain();
        FeedbackDocument document = new FeedbackDocument();
        FeedbackDocument saved = new FeedbackDocument();
        saved.setId(10L);

        when(feedbackDocumentMapper.toDocument(domain)).thenReturn(document);
        when(feedbackRepository.save(document)).thenReturn(saved);

        gateway.save(domain);

        assertEquals("10", domain.getId());
        verify(feedbackRepository).save(document);
    }

    @Test
    void delete_shouldDeleteById_whenIdIsValid() {
        FeedbackDomain domain = new FeedbackDomain();
        domain.setId("5");

        gateway.delete(domain);

        verify(feedbackRepository).deleteById(5L);
        verifyNoInteractions(feedbackDocumentMapper);
    }

    @Test
    void delete_shouldDeleteByEntity_whenIdIsInvalidButMappedEntityHasId() {
        FeedbackDomain domain = new FeedbackDomain();
        domain.setId("invalid");

        FeedbackDocument document = new FeedbackDocument();
        document.setId(20L);

        when(feedbackDocumentMapper.toDocument(domain))
                .thenReturn(document);

        gateway.delete(domain);

        verify(feedbackRepository).delete(document);
    }
}