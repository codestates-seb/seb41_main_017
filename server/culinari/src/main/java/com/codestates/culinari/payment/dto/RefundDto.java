package com.codestates.culinari.payment.dto;

import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.payment.entity.Refund;

public record RefundDto(
        String cancelReason
) {

    public static RefundDto of(String cancelReason) {
        return new RefundDto(cancelReason);
    }

    public Refund toEntity(OrderDetail orderDetail, String paymentKey) {
        return Refund.of(
                paymentKey,
                cancelReason,
                orderDetail
        );
    }
}
