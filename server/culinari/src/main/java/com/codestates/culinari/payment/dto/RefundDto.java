package com.codestates.culinari.payment.dto;

import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.payment.entity.Refund;

public record RefundDto(
        Long orderDetailId,
        String paymentKey,
        String cancelReason
) {

    public static RefundDto of(Long orderDetailId, String paymentKey, String cancelReason) {
        return new RefundDto(orderDetailId, paymentKey, cancelReason);
    }

    public Refund toEntity(OrderDetail orderDetail) {
        return Refund.of(
                paymentKey,
                cancelReason,
                orderDetail
        );
    }
}
