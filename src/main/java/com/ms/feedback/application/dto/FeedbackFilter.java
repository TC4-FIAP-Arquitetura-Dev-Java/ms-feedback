package com.ms.feedback.application.dto;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;


public record FeedbackFilter(
        String description,
        UrgencyTypeEnum urgencyType
){}