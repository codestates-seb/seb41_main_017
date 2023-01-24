package com.codestates.culinari.payment.dto.response.toss.detail;

// 휴대폰으로 결제하면 제공되는 휴대폰 결제 관련 정보
public record PaymentTossMobilePhoneDto(
        String customerMobilePhone, // 결제에 사용한 휴대폰 번호
        String settlementStatus,    // 정산 상태 [INCOMPLETED, COMPLETED]
        String receiptUrl   // 휴대폰 결제 내역 영수증을 확인할 수 있는 주소
) {
}
