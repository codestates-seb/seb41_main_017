package com.codestates.culinari.payment.dto.response;

public record PaymentCardResponse(
        String company,
        String number,
        String installmentPlanMonths,
        String isInterestFree,
        String approveNo,
        String useCardPoint,
        String cardType,
        String ownerType,
        String acquireStatus,
        String receiptUrl
) {
}
