package com.ms.feedback.domain.rules;

import com.ms.feedback.domain.exception.FieldRequiredException;
import com.ms.feedback.domain.model.FeedbackDomain;

public class RequiredFieldsRules {

    public static void checkRequiredFields(FeedbackDomain feedbackDomain) {
        if(isNullOrEmpty(feedbackDomain.getDescription()) &&
                isNullOrEmpty(feedbackDomain.getRating().toString()) &&
                isNullOrEmpty(feedbackDomain.getUrgencyType().toString())) {
            throw new FieldRequiredException("Todos os campos devem ser preenchidos!");
        }

        if(feedbackDomain.getRating() < 0){
            throw new FieldRequiredException("Nota deve ser maior ou igual a 0!");
        }

        if(feedbackDomain.getRating() > 10){
            throw new FieldRequiredException("Nota deve ser menor ou igual a 10!");
        }
    }

    private static boolean isNullOrEmpty(String field){
        return field == null || field.trim().isEmpty() ;
    }
}
