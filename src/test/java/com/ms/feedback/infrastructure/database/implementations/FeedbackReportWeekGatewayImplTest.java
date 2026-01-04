package com.ms.feedback.infrastructure.database.implementations;

import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;
import com.ms.feedback.infrastructure.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastructure.database.projection.ReportByDayProjection;
import com.ms.feedback.infrastructure.database.repositories.FeedbackRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackReportWeekGatewayImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private FeedbackDocumentMapper feedbackDocumentMapper;

    @InjectMocks
    private FeedbackReportWeekGatewayImpl gateway;

    @Test
    void getFeedbackWeekDomainCountDay_shouldMapProjectionToDomain() {
        Instant initial = Instant.parse("2024-11-01T00:00:00Z");
        Instant end = Instant.parse("2024-11-07T23:59:59Z");

        ReportByDayProjection projection = mock(ReportByDayProjection.class);

        when(projection.getDate()).thenReturn(LocalDate.of(2024, 11, 3));
        when(projection.getQuantity()).thenReturn(5L);

        when(feedbackRepository.generateReportWeekCountDay(initial, end))
                .thenReturn(List.of(projection));

        List<ReportByDayDomain> result =
                gateway.getFeedbackWeekDomainCountDay(initial, end);

        assertEquals(1, result.size());

        ReportByDayDomain domain = result.get(0);
        assertEquals(LocalDate.of(2024, 11, 3), domain.getDate());
        assertEquals(5L, domain.getQuantity());

        verify(feedbackRepository)
                .generateReportWeekCountDay(initial, end);
    }

    @Test
    void getFeedbackWeekDomainCountDay_shouldReturnEmptyList_whenNoData() {
        Instant initial = Instant.now();
        Instant end = Instant.now();

        when(feedbackRepository.generateReportWeekCountDay(initial, end))
                .thenReturn(List.of());

        List<ReportByDayDomain> result =
                gateway.getFeedbackWeekDomainCountDay(initial, end);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getFeedbackWeekCountUrgencyType_shouldDelegateToRepository() {
        Instant initial = Instant.parse("2024-11-01T00:00:00Z");
        Instant end = Instant.parse("2024-11-07T23:59:59Z");

        ReportByUrgencyTypeDomain domain =
                new ReportByUrgencyTypeDomain(null, 10L);

        when(feedbackRepository.generateReportWeekCountUrgencyType(initial, end))
                .thenReturn(List.of(domain));

        List<ReportByUrgencyTypeDomain> result =
                gateway.getFeedbackWeekCountUrgencyType(initial, end);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getQuantity());

        verify(feedbackRepository)
                .generateReportWeekCountUrgencyType(initial, end);
    }
}