package com.codestates.culinari.payment.dto.response;

import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.dto.PaymentDto;

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

    public static PaymentInfoResponse from(PaymentDto dto) {
        return PaymentInfoResponse.of(
                dto.payType(),
                dto.amount(),
                String.format("%019d", dto.orderDto().id()),
                dto.productName()
        );
    }
}
