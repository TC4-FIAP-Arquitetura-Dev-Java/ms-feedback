package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.usecase.GetFeedbackReportUseCase;
import com.ms.feedback.domain.domainService.GetFeedbackWeekDomainService;
import com.ms.feedback.domain.model.EvaluationByDayDomain;
import com.ms.feedback.domain.model.EvaluationByUrgencyDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;

import java.time.OffsetDateTime;
import java.util.List;

public class GetFeedbackReportUseCaseImpl implements GetFeedbackReportUseCase {

    private final GetFeedbackWeekDomainService getFeedbackWeekDomainService;

    public GetFeedbackReportUseCaseImpl(GetFeedbackWeekDomainService getFeedbackWeekDomainService) {
        this.getFeedbackWeekDomainService = getFeedbackWeekDomainService;
    }

    @Override
    public FeedbackReportDomain getFeedbackReportWeek(OffsetDateTime date) {
        FeedbackReportDomain report = new FeedbackReportDomain();

        report.setEvaluationsPerUrgency(enrichEvaluationsByUrgency(date));
        report.setEvaluationsPerDay(enrichEvaluationsByDay(date));

        return report;
    }

    private List<EvaluationByUrgencyDomain> enrichEvaluationsByUrgency(OffsetDateTime date) {

        return getFeedbackWeekDomainService
                .getFeedbackWeekCountUrgencyType(date)
                .stream()
                .map(this::toEvaluationByUrgency)
                .toList();
    }

    private EvaluationByUrgencyDomain toEvaluationByUrgency(
            ReportByUrgencyTypeDomain report) {

        EvaluationByUrgencyDomain evaluation = new EvaluationByUrgencyDomain();
        evaluation.setUrgencyTypeEnum(report.getUrgencyType());
        evaluation.setQuantity(report.getQuantity());
        return evaluation;
    }

    private List<EvaluationByDayDomain> enrichEvaluationsByDay(OffsetDateTime date) {

        return getFeedbackWeekDomainService
                .getFeedbackWeekDomainCountDay(date)
                .stream()
                .map(this::toEvaluationByDay)
                .toList();
    }

    private EvaluationByDayDomain toEvaluationByDay(
            ReportByDayDomain report) {

        EvaluationByDayDomain evaluation = new EvaluationByDayDomain();
        evaluation.setDay(report.getDate());
        evaluation.setQuantity(report.getQuantity());
        return evaluation;
    }
}
