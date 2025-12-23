package com.ms.feedback.domain.enuns;

public enum UrgencyTypeEnum {

    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3),
    URGENT("Urgent", 4);

    private final String description;
    private final int rating;

    UrgencyTypeEnum(String description, int rating) {
        this.description = description;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}

