package com.ms.feedback.domain.model;

import java.sql.Date;
import java.time.LocalDate;

public class ReportByDayDomain {

    private Long quantity;
    private LocalDate date;

    public ReportByDayDomain() {}

    public ReportByDayDomain(Date date, Long quantity) {
        this.date = date.toLocalDate();
        this.quantity = quantity;
    }

    public ReportByDayDomain(LocalDate date, Long quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
