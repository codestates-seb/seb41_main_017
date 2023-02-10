package com.codestates.culinari.order.constant;

import lombok.Getter;

public enum StatusType {
    ORDER_RECEIVED("주문 접수"),
    PREPARING_SHIPPING("배송 준비중"),
    START_SHIPPING("배송 출발"),
    ON_SHIPPING("배송중"),
    COMPLETE("배송 완료");

    @Getter
    private final String type;

    StatusType(String type) { this.type = type; }
}
