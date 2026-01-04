package com.ms.feedback.utils;

import java.time.Instant;
import java.time.OffsetDateTime;

public class DateRangeUtil {

    private DateRangeUtil() {}

    public static Instant dateInitialWeek(OffsetDateTime date) {
        return date
                .toLocalDate()           // pega o DIA no offset recebido
                .minusDays(7)            // volta 7 dias
                .atStartOfDay()          // 00:00:00
                .atOffset(date.getOffset())
                .toInstant();
    }

    public static Instant dateFinalWeek(OffsetDateTime date) {
        return date
                .toLocalDate()           // DIA no offset recebido
                .atTime(23, 59, 59, 999_999_999)
                .atOffset(date.getOffset())
                .toInstant();
    }
}
