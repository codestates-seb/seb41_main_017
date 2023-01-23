package com.codestates.culinari.payment.dto.response.toss.detail;

// 계좌이체로 결제했을 때 이체 정보가 담기는 객체
public record PaymentTossTransferDto(
        String bankCode,    // 은행 숫자 코드
        String settlementStatus // 정산 상태 [INCOMPLETED, COMPLETED]
) {
}
