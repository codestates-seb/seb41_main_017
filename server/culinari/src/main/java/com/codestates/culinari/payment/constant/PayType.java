package com.codestates.culinari.payment.constant;

import lombok.Getter;

public enum PayType {
    CARD("카드");

    @Getter
    private final String type;

    PayType(String type) { this.type = type; }
}
