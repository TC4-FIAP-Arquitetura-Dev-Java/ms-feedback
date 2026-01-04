package com.ms.feedback.entrypoint.controllers;

import com.ms.feedback.application.usecase.CreateFeedbackUseCase;
import com.ms.feedback.application.usecase.DeleteFeedbackUseCase;
import com.ms.feedback.application.usecase.GetFeedbackByIdUseCase;
import com.ms.feedback.application.usecase.GetFeedbackReportUseCase;
import com.ms.feedback.application.usecase.ListFeedbackUseCase;
import com.ms.feedback.application.usecase.UpdateFeedbackUseCase;
import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.domain.model.EvaluationByDayDomain;
import com.ms.feedback.domain.model.EvaluationByUrgencyDomain;
import com.ms.loginDomain.gen.model.DateRequestDto;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;
import com.ms.loginDomain.gen.model.PagedFeedbackResponseDto;
import com.ms.loginDomain.gen.model.UrgencyTypeEnumDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerCoverageTest {

    @Mock
    private GetFeedbackByIdUseCase getFeedbackByIdUseCase;

    @Mock
    private ListFeedbackUseCase listFeedbackUseCase;

    @Mock
    private CreateFeedbackUseCase createFeedbackUseCase;

    @Mock
    private DeleteFeedbackUseCase deleteFeedbackUseCase;

    @Mock
    private UpdateFeedbackUseCase updateFeedbackUseCase;

    @Mock
    private GetFeedbackReportUseCase getFeedbackReportUseCase;

    @InjectMocks
    private FeedbackController controller;

    @Test
    void shouldMapDomainToFeedbackResponseDto() {
        FeedbackDomain domain = new FeedbackDomain();
        domain.setDescription("Curso excelente");
        domain.setRating(9);
        domain.setUrgencyType(UrgencyTypeEnum.HIGH);

        when(getFeedbackByIdUseCase.getById("1"))
                .thenReturn(domain);

        ResponseEntity<FeedbackResponseDto> response =
                controller._getFeedbackById("1");

        FeedbackResponseDto body = response.getBody();

        assertNotNull(body);
        assertEquals("Curso excelente", body.getDescription());
        assertEquals(UrgencyTypeEnumDto.HIGH, body.getUrgencyType());
    }

    @Test
    void shouldMapListOfFeedbacksAndPagination() {
        FeedbackDomain domain = new FeedbackDomain();
        domain.setDescription("Bom");
        domain.setRating(7);
        domain.setUrgencyType(UrgencyTypeEnum.MEDIUM);

        Page<FeedbackDomain> page = new PageImpl<>(
                List.of(domain),
                PageRequest.of(0, 5),
                1
        );

        when(listFeedbackUseCase.findAll(any(), any()))
                .thenReturn(page);

        ResponseEntity<PagedFeedbackResponseDto> response =
                controller._listFeedbacks("Bom", UrgencyTypeEnumDto.MEDIUM, 5, 0);

        PagedFeedbackResponseDto body = response.getBody();

        assertNotNull(body);
        assertEquals(1, body.getContent().size());
        assertEquals("Bom", body.getContent().get(0).getDescription());
        assertEquals(1L, body.getTotalElements());
        assertEquals(5, body.getLimit());
        assertEquals(0, body.getOffset());
    }

    @Test
    void shouldMapFeedbackRequestDtoToDomainOnCreate() {
        FeedbackRequestDto request = new FeedbackRequestDto();
        request.setDescription("Novo feedback");
        request.setRating(new BigDecimal(10));
        request.setUrgencyType(UrgencyTypeEnumDto.LOW);

        doNothing().when(createFeedbackUseCase)
                .create(any(FeedbackDomain.class));

        ResponseEntity<Void> response =
                controller._createFeedback(request);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void shouldMapFeedbackRequestDtoToDomainOnUpdate() {
        FeedbackRequestDto request = new FeedbackRequestDto();
        request.setDescription("Atualizado");
        request.setRating(new BigDecimal(6));
        request.setUrgencyType(UrgencyTypeEnumDto.HIGH);

        doNothing().when(updateFeedbackUseCase)
                .update(any(), any());

        ResponseEntity<Void> response =
                controller._updateFeedback("10", request);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void shouldMapFeedbackReportDomainToResponseDto() {
        EvaluationByUrgencyDomain urgency = new EvaluationByUrgencyDomain();
        urgency.setUrgencyTypeEnum(UrgencyTypeEnum.HIGH);
        urgency.setQuantity(4L);

        EvaluationByDayDomain day = new EvaluationByDayDomain();
        day.setDay(LocalDate.now());
        day.setQuantity(2L);

        FeedbackReportDomain report = new FeedbackReportDomain();
        report.setEvaluationsPerUrgency(List.of(urgency));
        report.setEvaluationsPerDay(List.of(day));

        when(getFeedbackReportUseCase.getFeedbackReportWeek(any()))
                .thenReturn(report);

        DateRequestDto request = new DateRequestDto();
        request.setDate(OffsetDateTime.now());

        ResponseEntity<?> response =
                controller._report(request);

        assertNotNull(response.getBody());
    }
}