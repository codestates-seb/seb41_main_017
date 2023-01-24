package com.codestates.culinari.payment.dto.response.toss.detail;

import java.math.BigDecimal;

// 카드사의 즉시 할인 프로모션 정보, 즉시 할인 프로모션이 적용됐을 때만 생성
public record PaymentTossDiscountDto(
        BigDecimal amount   // 카드사의 즉시 할인 프로모션을 적용한 금액
) {
}
