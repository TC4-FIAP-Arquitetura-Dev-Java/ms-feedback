package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.exception.FeedbackNotFoundException;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteFeedbackUseCaseImplTest {

    @Mock
    private FeedbackGateway feedbackGateway;

    @InjectMocks
    private DeleteFeedbackUseCaseImpl deleteFeedbackUseCase;

    @Test
    void shouldDeleteFeedbackWhenItExists() {
        String id = "123";
        FeedbackDomain feedback = new FeedbackDomain();

        when(feedbackGateway.findById(id))
                .thenReturn(Optional.of(feedback));

        deleteFeedbackUseCase.delete(id);

        verify(feedbackGateway).findById(id);
        verify(feedbackGateway).delete(feedback);
        verifyNoMoreInteractions(feedbackGateway);
    }
}
