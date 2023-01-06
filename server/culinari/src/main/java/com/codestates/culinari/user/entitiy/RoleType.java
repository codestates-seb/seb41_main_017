package com.codestates.culinari.user.entitiy;

import lombok.Getter;

public enum RoleType {
    USER("일반 유저"),
    ADMIN("권리자");

    @Getter
    private final String type;

    RoleType(String type) { this.type = type; }
}
