package com.ms.feedback.infrastructure.database.repositories;

import com.ms.feedback.domain.model.ReportByUrgencyTypeDomain;
import com.ms.feedback.infrastructure.database.entities.FeedbackDocument;
import com.ms.feedback.infrastructure.database.projection.ReportByDayProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackDocument, Long>, JpaSpecificationExecutor<FeedbackDocument> {

    @Query("""
        SELECT new com.ms.feedback.domain.model.ReportByUrgencyTypeDomain(
            f.urgencyType,
            COUNT(f)
        )
        FROM FeedbackDocument f
        WHERE f.sentDate >= :dateInitial
          AND f.sentDate <= :dateFinal
        GROUP BY f.urgencyType
    """)
    List<ReportByUrgencyTypeDomain> generateReportWeekCountUrgencyType(
            @Param("dateInitial") Instant dateInitial,
            @Param("dateFinal") Instant dateFinal
    );

    @Query(
            value = """
        SELECT
            DATE(sent_date) AS date,
            COUNT(*)        AS quantity
        FROM feedback
        WHERE sent_date BETWEEN :dateInitial AND :dateFinal
        GROUP BY DATE(sent_date)
        ORDER BY DATE(sent_date) DESC
    """,
            nativeQuery = true
    )
    List<ReportByDayProjection> generateReportWeekCountDay(
            @Param("dateInitial") Instant dateInitial,
            @Param("dateFinal") Instant dateFinal
    );
}
