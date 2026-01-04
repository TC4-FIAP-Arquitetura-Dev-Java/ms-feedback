package com.ms.feedback.utils;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;

import java.time.OffsetDateTime;

public class FeedbackDomainBuilder {

    public static FeedbackDomain valid() {
        FeedbackDomain feedback = new FeedbackDomain();

        feedback.setId("1");
        feedback.setDescription("Curso excelente");
        feedback.setRating(5);
        feedback.setUrgencyType(UrgencyTypeEnum.LOW);
        feedback.setSentDate(OffsetDateTime.now());

        return feedback;
    }

    public static FeedbackDomain invalid() {
        FeedbackDomain feedback = new FeedbackDomain();

        feedback.setId("1");
        feedback.setDescription("Curso excelente");
        feedback.setRating(5);
        feedback.setUrgencyType(UrgencyTypeEnum.LOW);
        feedback.setSentDate(OffsetDateTime.now());

        return feedback;
    }
}