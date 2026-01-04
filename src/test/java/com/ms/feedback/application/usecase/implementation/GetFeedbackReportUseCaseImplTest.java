package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.domain.domainService.GetFeedbackWeekDomainService;
import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.EvaluationByDayDomain;
import com.ms.feedback.domain.model.EvaluationByUrgencyDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFeedbackReportUseCaseImplTest {

    @Mock
    private GetFeedbackWeekDomainService getFeedbackWeekDomainService;

    @InjectMocks
    private GetFeedbackReportUseCaseImpl getFeedbackReportUseCase;

    @Test
    void shouldReturnFeedbackReportWithEvaluationsByUrgencyAndDay() {
        OffsetDateTime date = OffsetDateTime.now();

        ReportByUrgencyTypeDomain urgencyReport =
                new ReportByUrgencyTypeDomain(UrgencyTypeEnum.HIGH, 5L);

        ReportByDayDomain dayReport = new ReportByDayDomain();
        dayReport.setDate(LocalDate.now());
        dayReport.setQuantity(3L);

        when(getFeedbackWeekDomainService.getFeedbackWeekCountUrgencyType(date))
                .thenReturn(List.of(urgencyReport));

        when(getFeedbackWeekDomainService.getFeedbackWeekDomainCountDay(date))
                .thenReturn(List.of(dayReport));

        FeedbackReportDomain result =
                getFeedbackReportUseCase.getFeedbackReportWeek(date);

        assertNotNull(result);

        List<EvaluationByUrgencyDomain> urgencyEvaluations =
                result.getEvaluationsPerUrgency();

        assertEquals(1, urgencyEvaluations.size());
        assertEquals(
                UrgencyTypeEnum.HIGH,
                urgencyEvaluations.get(0).getUrgencyTypeEnum()
        );
        assertEquals(5L, urgencyEvaluations.get(0).getQuantity());

        List<EvaluationByDayDomain> dayEvaluations =
                result.getEvaluationsPerDay();

        assertEquals(1, dayEvaluations.size());
        assertEquals(
                dayReport.getDate(),
                dayEvaluations.get(0).getDay()
        );
        assertEquals(3L, dayEvaluations.get(0).getQuantity());

        verify(getFeedbackWeekDomainService)
                .getFeedbackWeekCountUrgencyType(date);
        verify(getFeedbackWeekDomainService)
                .getFeedbackWeekDomainCountDay(date);
        verifyNoMoreInteractions(getFeedbackWeekDomainService);
    }
}