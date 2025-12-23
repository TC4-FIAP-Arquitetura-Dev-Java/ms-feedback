package com.ms.feedback.infrastrcture.database.entities;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Document(collection = "feedback")
public class FeedbackDocument {

    @Id
    private String id;

    private String description;

    private Integer rating;

    @Field("urgencyType")
    private UrgencyTypeEnum urgencyType;

    @CreatedDate
    private Instant sentDate;
}
