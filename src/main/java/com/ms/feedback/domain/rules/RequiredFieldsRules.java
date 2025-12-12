package com.ms.feedback.domain.rules;

import com.ms.feedback.domain.exception.FieldRequiredException;
import com.ms.feedback.domain.model.FeedbackDomain;

public class RequiredFieldsRules {

    public static void checkRequiredFields(FeedbackDomain feedbackDomain) {
        if(isNullOrEmpty(feedbackDomain.getDescricao()) &&
                isNullOrEmpty(feedbackDomain.getNota().toString()) &&
                isNullOrEmpty(feedbackDomain.getTipoUrgenciaEnum().toString())) {
            throw new FieldRequiredException("Required fields are mandatory");
        }
    }

    private static boolean isNullOrEmpty(String field){
        return field == null || field.trim().isEmpty() ;
    }
}
