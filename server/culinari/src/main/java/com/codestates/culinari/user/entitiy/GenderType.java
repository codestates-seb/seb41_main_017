package com.codestates.culinari.user.entitiy;

import lombok.Getter;

public enum GenderType {
    UNSELECTED("선택 안함"),
    MAN("남성"),
    WOMAN("여성");

    @Getter
    private final String type;

    GenderType(String type) { this.type = type; }
}
