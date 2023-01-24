package com.codestates.culinari.payment.dto.response.toss.detail;

import java.math.BigDecimal;

// 결제 취소 이력
public record PaymentTossCancelDto(
        BigDecimal cancelAmount,    // 결제를 취소한 금액
        String cancelReason,        // 결제를 취소한 이유 (Max 200)
        BigDecimal taxFreeAmount,   // 취소된 금액 중 면세 금액
        BigDecimal taxExemptionAmount,  // 취소된 금액 중 과세 제외 금액
        BigDecimal refundableAmount,    // 결제 취소 후 환불 가능한 잔액
        BigDecimal easyPayDiscountAmount,   // 간편결제 서비스의 포인트, 쿠폰, 즉시할인과 같은 적립식 결제 수단에서 취소된 금액
        String canceledAt,          // 결제 취소가 일어난 날짜와 시간 정보 [yyyy-MM-dd'T'HH:mm:ss±hh:mm]
        String transactionKey       // 취소 건의 키 값 (Max 64)
) {
}
