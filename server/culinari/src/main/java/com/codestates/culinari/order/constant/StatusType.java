package com.codestates.culinari.order.constant;

import lombok.Getter;

public enum StatusType {
    STAND_BY("대기 중"),
    SHIPPING("배송 중"),
    COMPLETE("배송 완료");

    @Getter
    private final String type;

    StatusType(String type) { this.type = type; }
}
