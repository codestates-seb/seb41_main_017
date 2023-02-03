package com.codestates.culinari.payment.dto.response.toss.detail;

import java.math.BigDecimal;

// 간편결제 정보
public record PaymentTossEasyPayDto(
        String provider,    // 선택한 간편결제사 코드
        BigDecimal amount,  // 간편결제 서비스에 등록된 카드, 계좌 중 하나로 결제한 금액
        BigDecimal discountAmount   // 간편결제 서비스의 적립 포인트나 쿠폰 등을 사용해서 즉시 할인된 금액
) {
}
