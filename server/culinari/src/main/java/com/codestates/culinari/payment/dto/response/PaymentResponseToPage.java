package com.codestates.culinari.payment.dto.response;

import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// TODO: 프론트 보고 Orders 추가 여부 생각
public record PaymentResponseToPage(
        Long id,
        PayType payType,
        BigDecimal amount,
        String productName,
        String paymentKey,
        LocalDateTime createdAt
) {

    public static PaymentResponseToPage of(Long id, PayType payType, BigDecimal amount, String productName, String paymentKey, LocalDateTime createdAt) {
        return new PaymentResponseToPage(id, payType, amount, productName, paymentKey, createdAt);
    }

    public static PaymentResponseToPage from(Payment entity) {
        return PaymentResponseToPage.of(
                entity.getId(),
                entity.getPayType(),
                entity.getAmount(),
                entity.getProductName(),
                entity.getPaymentKey(),
                entity.getOrder().getCreatedAt()
        );
    }
}
