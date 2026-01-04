package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFeedbackUseCaseImplTest {

    @Mock
    private FeedbackGateway feedbackGateway;

    @InjectMocks
    private GetFeedbackUseCaseImpl getFeedbackUseCase;

    @Test
    void shouldReturnFeedbackWhenIdExists() {
        // given
        String id = "123";
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setDescription("Excelente curso");
        feedback.setRating(5);

        when(feedbackGateway.findById(id))
                .thenReturn(Optional.of(feedback));

        FeedbackDomain result = getFeedbackUseCase.getById(id);

        assertNotNull(result);
        assertEquals("Excelente curso", result.getDescription());
        assertEquals(5, result.getRating());

        verify(feedbackGateway).findById(id);
        verifyNoMoreInteractions(feedbackGateway);
    }

    @Test
    void shouldReturnNullWhenIdDoesNotExist() {
        String id = "999";

        when(feedbackGateway.findById(id))
                .thenReturn(Optional.empty());

        FeedbackDomain result = getFeedbackUseCase.getById(id);

        assertNull(result);

        verify(feedbackGateway).findById(id);
        verifyNoMoreInteractions(feedbackGateway);
    }
}