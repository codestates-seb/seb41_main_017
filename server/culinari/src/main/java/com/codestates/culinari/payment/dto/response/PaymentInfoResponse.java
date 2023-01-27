package com.codestates.culinari.payment.dto.response;

import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.entity.Payment;

import java.math.BigDecimal;

public record PaymentInfoResponse(
        PayType payType,
        BigDecimal amount,
        String orderId,
        String productName
) {

    public static PaymentInfoResponse of(PayType payType, BigDecimal amount, String orderId, String productName) {
        return new PaymentInfoResponse(payType, amount, orderId, productName);
    }

    public static PaymentInfoResponse from(Payment entity) {
        return PaymentInfoResponse.of(
                entity.getPayType(),
                entity.getAmount(),
                String.format("%019d", entity.getOrder().getId()),
                entity.getProductName()
        );
    }
}
