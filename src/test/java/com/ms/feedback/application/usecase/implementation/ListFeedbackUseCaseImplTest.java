package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListFeedbackUseCaseImplTest {

    @Mock
    private FeedbackGateway feedbackGateway;

    @InjectMocks
    private ListFeedbackUseCaseImpl listFeedbackUseCase;

    @Test
    void shouldReturnFeedbackPageWhenFindAllIsCalled() {
        FeedbackFilter filter = new FeedbackFilter(null, null);
        Pageable pageable = PageRequest.of(0, 10);

        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setDescription("Ótimo curso");
        feedback.setRating(5);

        Page<FeedbackDomain> page = new PageImpl<>(
                List.of(feedback),
                pageable,
                1
        );

        when(feedbackGateway.findAll(filter, pageable))
                .thenReturn(page);

        Page<FeedbackDomain> result =
                listFeedbackUseCase.findAll(filter, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Ótimo curso", result.getContent().get(0).getDescription());

        verify(feedbackGateway).findAll(filter, pageable);
        verifyNoMoreInteractions(feedbackGateway);
    }
}