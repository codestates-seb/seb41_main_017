package com.codestates.culinari.payment.dto.response.toss.detail;

// 상품권으로 결제하면 제공되는 상품권 결제 관련 정보
public record PaymentTossGiftCertificateDto(
        String approveNo,   // 결제 승인번호 (Max 8)
        String settlementStatus // 정산 상태 [INCOMPLETED, COMPLETED]
) {
}
