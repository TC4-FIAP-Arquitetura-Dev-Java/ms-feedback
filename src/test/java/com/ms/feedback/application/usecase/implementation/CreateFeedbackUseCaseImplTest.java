package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.utils.FeedbackDomainBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateFeedbackUseCaseImplTest {

    @Mock
    private FeedbackGateway feedbackGateway;

    @InjectMocks
    private CreateFeedbackUseCaseImpl useCase;

    @Test
    void shouldCreateFeedbackWhenDataIsValid() {
        FeedbackDomain feedback = FeedbackDomainBuilder.valid();

        useCase.create(feedback);

        verify(feedbackGateway).save(feedback);
    }
}