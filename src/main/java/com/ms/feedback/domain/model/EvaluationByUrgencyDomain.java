package com.ms.feedback.domain.model;

import com.ms.feedback.domain.enuns.UrgencyTypeEnum;

public class EvaluationByUrgencyDomain {

    private UrgencyTypeEnum urgencyTypeEnum;
    private Long quantity;

    public EvaluationByUrgencyDomain() {}

    public UrgencyTypeEnum getUrgencyTypeEnum() {
        return urgencyTypeEnum;
    }

    public void setUrgencyTypeEnum(UrgencyTypeEnum urgencyTypeEnum) {
        this.urgencyTypeEnum = urgencyTypeEnum;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
