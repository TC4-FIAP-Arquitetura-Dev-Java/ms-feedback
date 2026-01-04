package com.ms.feedback.domain.model;

import java.time.LocalDate;

public class EvaluationByDayDomain {

    private LocalDate day;
    private Long quantity;

    public EvaluationByDayDomain() {}

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
