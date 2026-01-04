package com.ms.feedback.application.usecase;

import com.ms.feedback.domain.model.FeedbackReportDomain;

import java.time.OffsetDateTime;

public interface GetFeedbackReportUseCase {

    FeedbackReportDomain getFeedbackReportWeek(OffsetDateTime date);
}
