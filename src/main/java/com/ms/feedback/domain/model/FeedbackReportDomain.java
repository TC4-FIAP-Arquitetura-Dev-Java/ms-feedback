package com.ms.feedback.domain.model;

import java.util.List;

public class FeedbackReportDomain {

    private List<EvaluationByDayDomain> evaluationsPerDay;
    private List<EvaluationByUrgencyDomain> evaluationsPerUrgency;

    public FeedbackReportDomain() {}

    public FeedbackReportDomain(List<EvaluationByDayDomain> evaluationsPerDay, List<EvaluationByUrgencyDomain> evaluationsPerUrgency) {
        this.evaluationsPerDay = evaluationsPerDay;
        this.evaluationsPerUrgency = evaluationsPerUrgency;
    }

    public List<EvaluationByDayDomain> getEvaluationsPerDay() {
        return evaluationsPerDay;
    }

    public void setEvaluationsPerDay(List<EvaluationByDayDomain> evaluationsPerDay) {
        this.evaluationsPerDay = evaluationsPerDay;
    }

    public List<EvaluationByUrgencyDomain> getEvaluationsPerUrgency() {
        return evaluationsPerUrgency;
    }

    public void setEvaluationsPerUrgency(List<EvaluationByUrgencyDomain> evaluationsPerUrgency) {
        this.evaluationsPerUrgency = evaluationsPerUrgency;
    }
}
