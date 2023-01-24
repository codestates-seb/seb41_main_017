package com.codestates.culinari.payment.dto.response.toss.detail;

import java.math.BigDecimal;

// 카드로 결제하면 제공되는 카드 관련 정보
public record PaymentTossCardDto(
        BigDecimal amount,  // 카드로 결제한 금액
        String issuerCode,  // 카드 발급사 숫자 코드
        String acquirerCode,    // 카드 매입사 숫자 코드
        String number,      // 카드 번호 (Max 20)
        Integer installmentPlanMonths,  // 할부 개월 수 (일시불은 0)
        String approveNo,   // 카드사 승인 번호 (Max 8)
        String cardType,    // 카드 종류 [신용, 체크, 기프트]
        String ownerType,   // 카드 소유자 타입 [개인, 법인]
        String acquireStatus,   // 카드 결제의 매입 상태 [READY, REQUESTED, COMPLETED, CANCEL_REQUESTED, CANCELED]
        Boolean isInterestFree, // 무이자 할부의 적용 여부
        String interestPayer    // 무이자 할부가 적용된 결제에서 할부 수수료를 부담하는주체 [BUYER, CARD_COMPANY, MERCHANT]
) {
}
