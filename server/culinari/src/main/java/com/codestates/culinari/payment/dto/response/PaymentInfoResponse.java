package com.codestates.culinari.payment.dto.response;

import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.entity.Payment;

import java.math.BigDecimal;

public record PaymentInfoResponse(
        PayType payType,
        BigDecimal amount,
        String orderId,
        String orderName,
        String successUrl,
        String failUrl
) {

    public static PaymentInfoResponse of(PayType payType, BigDecimal amount, String orderId, String orderName, String successUrl, String failUrl) {
        return new PaymentInfoResponse(payType, amount, orderId, orderName, successUrl, failUrl);
    }

    public static PaymentInfoResponse from(Payment entity, String successUrl, String failUrl) {
        return PaymentInfoResponse.of(
                entity.getPayType(),
                entity.getAmount(),
                String.format("%019d", entity.getOrder().getId()),
                entity.getProductName(),
                successUrl,
                failUrl
        );
    }
}
