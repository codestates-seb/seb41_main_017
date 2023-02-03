package com.codestates.culinari.payment.dto.response;

import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.entity.Payment;

import java.math.BigDecimal;

public record PaymentInfoResponse(
        PayType payType,
        BigDecimal amount,
        String orderId,
        String orderName
) {

    public static PaymentInfoResponse of(PayType payType, BigDecimal amount, String orderId, String orderName) {
        return new PaymentInfoResponse(payType, amount, orderId, orderName);
    }

    public static PaymentInfoResponse from(Payment entity) {
        return PaymentInfoResponse.of(
                entity.getPayType(),
                entity.getAmount(),
                entity.getOrder().getId(),
                entity.getProductName()
        );
    }
}
