package com.ms.feedback.domain.model;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;

import java.time.OffsetDateTime;

public class FeedbackDomain {

    private String id;
    private String description;
    private Integer rating;
    private UrgencyTypeEnum urgencyType;
    private OffsetDateTime sentDate;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public UrgencyTypeEnum getUrgencyType() {
        return urgencyType;
    }

    public void setUrgencyType(UrgencyTypeEnum urgencyType) {
        this.urgencyType = urgencyType;
    }

    public OffsetDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(OffsetDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
