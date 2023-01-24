package com.codestates.culinari.payment.dto.response.toss.detail;

import java.math.BigDecimal;

// 현금영수증 정보
public record PaymentTossCashReceiptDto(
        String receiptKey,  // 현금영수증의 키 값 (Max 200)
        String type,    // 현금영수증의 종류 [소득공제, 지출증빙]
        BigDecimal amount,  // 현금영수증 처리된 금액
        BigDecimal taxFreeAmount,   // 면세 처리된 금액
        String issueNumber, // 현금영수증 발급 번호 (Max 9)
        String receiptUrl   // 발행된 현금영수증을 확인할 수 있는 주소
) {
}
