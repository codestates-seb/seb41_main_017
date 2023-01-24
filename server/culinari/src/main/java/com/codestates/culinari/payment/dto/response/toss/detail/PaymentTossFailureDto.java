package com.codestates.culinari.payment.dto.response.toss.detail;

// 결제 실패 정보
public record PaymentTossFailureDto(
        String code,    // 오류 타입을 보여주는 에러 코드
        String message  // 에러 메시지
) {
}
