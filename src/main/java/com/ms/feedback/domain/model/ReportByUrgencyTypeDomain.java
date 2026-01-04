package com.ms.feedback.domain.model;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;

public class ReportByUrgencyTypeDomain {

    private UrgencyTypeEnum urgencyType;
    private Long quantity;

    public ReportByUrgencyTypeDomain(UrgencyTypeEnum urgencyType, Long quantity) {
        this.urgencyType = urgencyType;
        this.quantity = quantity;
    }

    public UrgencyTypeEnum getUrgencyType() {
        return urgencyType;
    }

    public void setUrgencyType(UrgencyTypeEnum urgencyType) {
        this.urgencyType = urgencyType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}