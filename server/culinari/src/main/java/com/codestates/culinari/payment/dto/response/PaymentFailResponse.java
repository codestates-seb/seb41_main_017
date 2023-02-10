package com.codestates.culinari.payment.dto.response;

public record PaymentFailResponse(
        String errorCode,
        String errorMsg,
        String orderId
) {

    public static PaymentFailResponse of(String errorCode, String errorMsg, String orderId) {
        return new PaymentFailResponse(errorCode, errorMsg, orderId);
    }
}
