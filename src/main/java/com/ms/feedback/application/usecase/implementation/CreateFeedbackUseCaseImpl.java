package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.gateway.FeedbackSendEmailGateway;
import com.ms.feedback.application.usecase.CreateFeedbackUseCase;
import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.rules.RequiredFieldsRules;

public class CreateFeedbackUseCaseImpl implements CreateFeedbackUseCase {

    private final FeedbackGateway feedbackGateway;
    private final FeedbackSendEmailGateway feedbackSendEmailGateway;

    public CreateFeedbackUseCaseImpl(FeedbackGateway feedbackGateway,
                                     FeedbackSendEmailGateway feedbackSendEmailGateway) {
        this.feedbackGateway = feedbackGateway;
        this.feedbackSendEmailGateway = feedbackSendEmailGateway;
    }

    @Override
    public void create(FeedbackDomain feedback) {
        RequiredFieldsRules.checkRequiredFields(feedback);

        if(feedback.getUrgencyType().equals(UrgencyTypeEnum.URGENT)) {
            feedbackSendEmailGateway.sendEmail(feedback);
        }

        feedbackGateway.save(feedback);
    }
}
