package com.codestates.culinari.payment.dto.response;

public record PaymentSuccessResponse(
        String mId,
        String version,
        String paymentKey,
        String orderId,
        String orderName,
        String currency,
        String method,
        String totalAmount,
        String balanceAmount,
        String suppliedAmount,
        String vat,
        String status,
        String requestedAt,
        String approvedAt,
        String useEscrow,
        String cultureExpense,
        PaymentCardResponse card,
        String type
) {
}
