package com.ms.feedback.infrastrcture.database.projection;

import java.time.LocalDate;

public interface ReportByDayProjection {
    LocalDate getDate();
    Long getQuantity();
}