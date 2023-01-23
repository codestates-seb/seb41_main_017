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
    public static PaymentCardResponse of(
            String company, String number, String installmentPlanMonths, String isInterestFree, String approveNo,
            String useCardPoint, String cardType, String ownerType, String acquireStatus, String receiptUrl
    ) {
        return new PaymentCardResponse(
                company, number, installmentPlanMonths, isInterestFree, approveNo, useCardPoint, cardType, ownerType,
                acquireStatus, receiptUrl
        );
    }
}
