package com.ms.feedback.infrastrcture.config.security;

public enum Role {
    ADMIN,
    USER,
    GUEST;

    public String toAuthority() {
        return "ROLE_" + this.name();
    }

    public static Role fromAuthority(String authority) {
        String name = authority.startsWith("ROLE_") ? authority.substring(5) : authority;
        return Role.valueOf(name);
    }
}
