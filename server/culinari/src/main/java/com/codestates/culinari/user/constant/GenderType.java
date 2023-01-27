package com.codestates.culinari.user.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum GenderType {
    MAN("남성"),
    WOMAN("여성");

    @Getter
    private final String type;

    GenderType(String type) {
        this.type = type;
    }

    @JsonCreator
    public static GenderType from(String value) {
        for (GenderType genderType : GenderType.values()) {
            if (genderType.getValue().equals(value)) {
                return genderType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return type;
    }
}
