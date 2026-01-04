package com.ms.feedback.domain.domainService.impl;

import com.ms.feedback.application.gateway.FeedbackReportWeekGateway;
import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;
import com.ms.feedback.utils.DateRangeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFeedbackWeekDomainServiceImplTest {

    @Mock
    private FeedbackReportWeekGateway feedbackReportWeekGateway;

    @InjectMocks
    private GetFeedbackWeekDomainServiceImpl domainService;

    @Test
    void shouldReturnReportByDayForGivenWeek() {
        OffsetDateTime date = OffsetDateTime.of(
                LocalDate.of(2026, 1, 1),
                java.time.LocalTime.NOON,
                ZoneOffset.UTC
        );

        Instant dateInitial = DateRangeUtil.dateInitialWeek(date);
        Instant dateFinal = DateRangeUtil.dateFinalWeek(date);

        ReportByDayDomain report = new ReportByDayDomain();
        report.setDate(LocalDate.of(2025, 12, 30));
        report.setQuantity(4L);

        when(feedbackReportWeekGateway
                .getFeedbackWeekDomainCountDay(dateInitial, dateFinal))
                .thenReturn(List.of(report));

        List<ReportByDayDomain> result =
                domainService.getFeedbackWeekDomainCountDay(date);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(4L, result.get(0).getQuantity());

        verify(feedbackReportWeekGateway)
                .getFeedbackWeekDomainCountDay(dateInitial, dateFinal);
        verifyNoMoreInteractions(feedbackReportWeekGateway);
    }

    @Test
    void shouldReturnReportByUrgencyTypeForGivenWeek() {
        OffsetDateTime date = OffsetDateTime.now();

        Instant dateInitial = DateRangeUtil.dateInitialWeek(date);
        Instant dateFinal = DateRangeUtil.dateFinalWeek(date);

        ReportByUrgencyTypeDomain report =
                new ReportByUrgencyTypeDomain(UrgencyTypeEnum.HIGH, 7L);

        when(feedbackReportWeekGateway
                .getFeedbackWeekCountUrgencyType(dateInitial, dateFinal))
                .thenReturn(List.of(report));

        List<ReportByUrgencyTypeDomain> result =
                domainService.getFeedbackWeekCountUrgencyType(date);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(UrgencyTypeEnum.HIGH, result.get(0).getUrgencyType());
        assertEquals(7L, result.get(0).getQuantity());

        verify(feedbackReportWeekGateway)
                .getFeedbackWeekCountUrgencyType(dateInitial, dateFinal);
        verifyNoMoreInteractions(feedbackReportWeekGateway);
    }
}