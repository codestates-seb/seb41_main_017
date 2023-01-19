package com.codestates.culinari.payment.dto;

import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.payment.entity.Refund;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RefundDto(
        @Positive Long orderDetailId,
        @NotBlank String paymentKey,
        @NotBlank String cancelReason
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
