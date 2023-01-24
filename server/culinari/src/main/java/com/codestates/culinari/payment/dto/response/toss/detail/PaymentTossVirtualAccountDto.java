package com.codestates.culinari.payment.dto.response.toss.detail;

// 가상계좌로 결제하면 제공되는 가상계좌 관련 정보
public record PaymentTossVirtualAccountDto(
        String accountType,     // 가상계좌 타입 [일반, 고정]
        String accountNumber,   // 발급된 계좌 번호 (Max 20)
        String bankCode,    // 가상계좌 은행 숫자 코드
        String customerName,    // 가상계좌를 발급한 고객 이름 (Max 100)
        String dueDate,     // 입금 기한
        String refundStatus,    // 환불 처리 상태 [NONE, PENDING, FAILED, PARTIAL_FAILED, COMPLETED
        Boolean expired,    // 가상계좌가 만료 여부
        String settlementStatus // 정산 상태 [INCOMPLETED, COMPLETED]
) {
}
