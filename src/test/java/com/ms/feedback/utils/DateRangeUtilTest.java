package com.ms.feedback.utils;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class DateRangeUtilTest {

    @Test
    void shouldReturnInitialDateOfWeekWithSameOffset() {
        OffsetDateTime inputDate =
                OffsetDateTime.of(
                        2024, 11, 10,
                        15, 30, 0, 0,
                        ZoneOffset.ofHours(-3)
                );

        Instant result = DateRangeUtil.dateInitialWeek(inputDate);

        OffsetDateTime expected =
                OffsetDateTime.of(
                        2024, 11, 3,
                        0, 0, 0, 0,
                        ZoneOffset.ofHours(-3)
                );

        assertEquals(expected.toInstant(), result);
    }

    @Test
    void shouldReturnFinalDateOfWeekWithEndOfDayPrecision() {
        OffsetDateTime inputDate =
                OffsetDateTime.of(
                        2024, 11, 10,
                        10, 0, 0, 0,
                        ZoneOffset.ofHours(-3)
                );

        Instant result = DateRangeUtil.dateFinalWeek(inputDate);

        OffsetDateTime expected =
                OffsetDateTime.of(
                        2024, 11, 10,
                        23, 59, 59, 999_999_999,
                        ZoneOffset.ofHours(-3)
                );

        assertEquals(expected.toInstant(), result);
    }

    @Test
    void shouldWorkWithPositiveOffset() {
        OffsetDateTime inputDate =
                OffsetDateTime.of(
                        2024, 6, 20,
                        12, 0, 0, 0,
                        ZoneOffset.ofHours(+2)
                );

        Instant initial = DateRangeUtil.dateInitialWeek(inputDate);
        Instant end = DateRangeUtil.dateFinalWeek(inputDate);

        OffsetDateTime expectedInitial =
                OffsetDateTime.of(
                        2024, 6, 13,
                        0, 0, 0, 0,
                        ZoneOffset.ofHours(+2)
                );

        OffsetDateTime expectedEnd =
                OffsetDateTime.of(
                        2024, 6, 20,
                        23, 59, 59, 999_999_999,
                        ZoneOffset.ofHours(+2)
                );

        assertEquals(expectedInitial.toInstant(), initial);
        assertEquals(expectedEnd.toInstant(), end);
    }

    @Test
    void shouldAlwaysReturnInitialBeforeFinal() {
        OffsetDateTime inputDate =
                OffsetDateTime.now(ZoneOffset.ofHours(-3));

        Instant initial = DateRangeUtil.dateInitialWeek(inputDate);
        Instant end = DateRangeUtil.dateFinalWeek(inputDate);

        assertTrue(initial.isBefore(end));
    }
}