package com.ms.feedback.infrastructure.database.projection;

import java.time.LocalDate;

public interface ReportByDayProjection {
    LocalDate getDate();
    Long getQuantity();
}