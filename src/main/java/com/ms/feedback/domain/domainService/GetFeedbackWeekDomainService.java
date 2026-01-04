package com.ms.feedback.domain.domainService;

import com.ms.feedback.domain.model.ReportByDayDomain;
import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;

import java.time.OffsetDateTime;
import java.util.List;

public interface GetFeedbackWeekDomainService {

    List<ReportByUrgencyTypeDomain> getFeedbackWeekCountUrgencyType(OffsetDateTime date);
    List<ReportByDayDomain> getFeedbackWeekDomainCountDay(OffsetDateTime date);
}
