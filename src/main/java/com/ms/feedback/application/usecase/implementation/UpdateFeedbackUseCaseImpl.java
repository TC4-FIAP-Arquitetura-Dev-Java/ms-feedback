package com.ms.feedback.application.usecase.implementation;

import com.ms.feedback.application.gateway.FeedbackGateway;
import com.ms.feedback.application.usecase.UpdateFeedbackUseCase;
import com.ms.feedback.domain.exception.FeedbackNotFoundException;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.rules.RequiredFieldsRules;

public class UpdateFeedbackUseCaseImpl implements UpdateFeedbackUseCase {

    private final FeedbackGateway  feedbackGateway;

    public UpdateFeedbackUseCaseImpl(FeedbackGateway feedbackGateway) {
        this.feedbackGateway = feedbackGateway;
    }

    @Override
    public void update(String id, FeedbackDomain feedback) {
        RequiredFieldsRules.checkRequiredFields(feedback);
        FeedbackDomain domain = feedbackGateway.findById(id).orElse(null);

        if(domain != null) {
            domain.setDescricao(feedback.getDescricao());
            domain.setNota(feedback.getNota());
            domain.setTipoUrgenciaEnum(feedback.getTipoUrgenciaEnum());
            feedbackGateway.save(domain);
        }else{
            throw new FeedbackNotFoundException("Feedback not found!");
        }
    }
}
