package com.codestates.culinari.payment.dto.request;

import com.codestates.culinari.payment.dto.RefundDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RefundRequest (
        @Positive Long orderDetailId,
        @NotBlank String paymentKey,
        @NotBlank String cancelReason
) {

    public static RefundRequest of(Long orderDetailId, String paymentKey, String cancelReason) {
        return new RefundRequest(orderDetailId, paymentKey, cancelReason);
    }

    public RefundDto toDto() {
        return RefundDto.of(
                orderDetailId,
                paymentKey,
                cancelReason
        );
    }
}
