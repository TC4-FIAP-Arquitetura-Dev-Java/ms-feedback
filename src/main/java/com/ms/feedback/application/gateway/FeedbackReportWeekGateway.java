package com.ms.feedback.application.gateway;

import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;

import java.time.Instant;
import java.util.List;

public interface FeedbackReportWeekGateway {

    FeedbackReportDomain findFeedbackReportsWeek();

    List<ReportByDayDomain> getFeedbackWeekDomainCountDay(Instant dateInitial, Instant dateFinal);
    List<ReportByUrgencyTypeDomain> getFeedbackWeekCountUrgencyType(Instant dateInitial, Instant dateFinal);

}
