package com.codestates.culinari.user.constant;

import lombok.Getter;

public enum RoleType {
    USER("일반 유저"),
    ADMIN("관리자");

    @Getter
    private final String type;

    RoleType(String type) { this.type = type; }
}
