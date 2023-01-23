package com.codestates.culinari.payment.dto.response.toss.detail;

// 발행된 영수증 정보
public record PaymentTossReceiptDto(
        String url  // 영수증을 확인할 수 있는 주소
) {
}
