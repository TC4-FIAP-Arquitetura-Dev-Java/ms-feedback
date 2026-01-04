package com.ms.feedback.domain.rules;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.exception.FieldRequiredException;
import com.ms.feedback.domain.model.FeedbackDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequiredFieldsRulesTest {

    @Test
    void shouldThrowExceptionWhenRatingIsLessThanZero() {
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setDescription("Bom curso");
        feedback.setRating(-1);
        feedback.setUrgencyType(UrgencyTypeEnum.LOW);

        assertThrows(
                FieldRequiredException.class,
                () -> RequiredFieldsRules.checkRequiredFields(feedback)
        );
    }

    @Test
    void shouldThrowExceptionWhenRatingIsGreaterThanTen() {
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setDescription("Bom curso");
        feedback.setRating(11);
        feedback.setUrgencyType(UrgencyTypeEnum.MEDIUM);

        assertThrows(
                FieldRequiredException.class,
                () -> RequiredFieldsRules.checkRequiredFields(feedback)
        );
    }

    @Test
    void shouldNotThrowExceptionWhenFieldsAreValid() {
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setDescription("Excelente curso");
        feedback.setRating(8);
        feedback.setUrgencyType(UrgencyTypeEnum.HIGH);

        assertDoesNotThrow(
                () -> RequiredFieldsRules.checkRequiredFields(feedback)
        );
    }
}