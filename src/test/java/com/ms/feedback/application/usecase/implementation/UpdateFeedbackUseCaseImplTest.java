package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.exception.FeedbackNotFoundException;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateFeedbackUseCaseImplTest {

    @Mock
    private FeedbackGateway feedbackGateway;

    @InjectMocks
    private UpdateFeedbackUseCaseImpl updateFeedbackUseCase;

    @Test
    void shouldUpdateFeedbackSuccessfully() {
        String id = "123";

        FeedbackDomain existingFeedback = new FeedbackDomain();
        existingFeedback.setDescription("Old description");
        existingFeedback.setRating(2);
        existingFeedback.setUrgencyType(UrgencyTypeEnum.LOW);

        FeedbackDomain updatedFeedback = new FeedbackDomain();
        updatedFeedback.setDescription("New description");
        updatedFeedback.setRating(5);
        updatedFeedback.setUrgencyType(UrgencyTypeEnum.HIGH);

        when(feedbackGateway.findById(id))
                .thenReturn(Optional.of(existingFeedback));

        updateFeedbackUseCase.update(id, updatedFeedback);

        assertEquals("New description", existingFeedback.getDescription());
        assertEquals(5, existingFeedback.getRating());
        assertEquals(UrgencyTypeEnum.HIGH, existingFeedback.getUrgencyType());

        verify(feedbackGateway).findById(id);
        verify(feedbackGateway).save(existingFeedback);
        verifyNoMoreInteractions(feedbackGateway);
    }

    @Test
    void shouldThrowExceptionWhenFeedbackNotFound() {
        String id = "123";
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setDescription("Desc");
        feedback.setRating(3);
        feedback.setUrgencyType(UrgencyTypeEnum.MEDIUM);

        when(feedbackGateway.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                FeedbackNotFoundException.class,
                () -> updateFeedbackUseCase.update(id, feedback)
        );

        verify(feedbackGateway).findById(id);
        verify(feedbackGateway, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenRequiredFieldsAreInvalid() {
        String id = "123";
        FeedbackDomain invalidFeedback = new FeedbackDomain();

        assertThrows(
                RuntimeException.class,
                () -> updateFeedbackUseCase.update(id, invalidFeedback)
        );

        verifyNoInteractions(feedbackGateway);
    }
}