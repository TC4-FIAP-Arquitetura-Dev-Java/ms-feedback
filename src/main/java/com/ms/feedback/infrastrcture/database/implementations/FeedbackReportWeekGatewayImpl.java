package com.ms.feedback.infrastrcture.database.implementations;

import com.ms.feedback.application.gateway.FeedbackReportWeekGateway;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;
import com.ms.feedback.infrastrcture.database.mappers.FeedbackDocumentMapper;
import com.ms.feedback.infrastrcture.database.projection.ReportByDayProjection;
import com.ms.feedback.infrastrcture.database.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackReportWeekGatewayImpl implements FeedbackReportWeekGateway {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackDocumentMapper feedbackDocumentMapper;

    @Override
    public FeedbackReportDomain findFeedbackReportsWeek() {
        return null;
    }

    @Override
    public List<ReportByDayDomain> getFeedbackWeekDomainCountDay(Instant dateInitial, Instant dateFinal) {
        return feedbackRepository
                .generateReportWeekCountDay(dateInitial, dateFinal)
                .stream()
                .map(this::toReportByDayDomain)
                .toList();
    }

    @Override
    public List<ReportByUrgencyTypeDomain> getFeedbackWeekCountUrgencyType(Instant dateInitial, Instant dateFinal) {
        return feedbackRepository.generateReportWeekCountUrgencyType(dateInitial, dateFinal);
    }

    private ReportByDayDomain toReportByDayDomain(
            ReportByDayProjection projection) {
        ReportByDayDomain domain = new ReportByDayDomain();
        domain.setDate(projection.getDate());
        domain.setQuantity(projection.getQuantity());
        return domain;
    }
}
