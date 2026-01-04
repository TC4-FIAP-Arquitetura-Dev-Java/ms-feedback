package com.ms.feedback.infrastructure.database.entities;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@Table(name = "feedback")
@EntityListeners(AuditingEntityListener.class)
public class FeedbackDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String description;

    private Integer rating;

    @Column(name = "urgency_type")
    @Enumerated(EnumType.STRING)
    private UrgencyTypeEnum urgencyType;

    @CreatedDate
    @Column(name = "sent_date")
    private Instant sentDate;
}
