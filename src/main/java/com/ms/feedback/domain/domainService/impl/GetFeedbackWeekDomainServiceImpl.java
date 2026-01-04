package com.ms.feedback.domain.domainService.impl;

import com.ms.feedback.application.gateway.FeedbackReportWeekGateway;
import com.ms.feedback.domain.domainService.GetFeedbackWeekDomainService;
import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;
import com.ms.feedback.utils.DateRangeUtil;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

public class GetFeedbackWeekDomainServiceImpl implements GetFeedbackWeekDomainService {

    private final FeedbackReportWeekGateway feedbackReportWeekGateway;

    public GetFeedbackWeekDomainServiceImpl(FeedbackReportWeekGateway feedbackReportWeekGateway) {
        this.feedbackReportWeekGateway = feedbackReportWeekGateway;
    }

    @Override
    public List<ReportByDayDomain> getFeedbackWeekDomainCountDay(OffsetDateTime date) {
        Result result = getDateWeek(date);
        return feedbackReportWeekGateway.getFeedbackWeekDomainCountDay(result.dateInitial(), result.dateFinal());
    }

    @Override
    public List<ReportByUrgencyTypeDomain> getFeedbackWeekCountUrgencyType(OffsetDateTime date) {
        Result result = getDateWeek(date);
        return feedbackReportWeekGateway.getFeedbackWeekCountUrgencyType(result.dateInitial(), result.dateFinal());
    }

    private static Result getDateWeek(OffsetDateTime date) {
        Instant dateInitial = DateRangeUtil.dateInitialWeek(date);
        Instant dateFinal = DateRangeUtil.dateFinalWeek(date);
        Result result = new Result(dateInitial, dateFinal);
        return result;
    }

    private record Result(Instant dateInitial, Instant dateFinal) {
    }
}
