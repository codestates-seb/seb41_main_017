package com.codestates.culinari.payment.dto.response.toss;

import com.codestates.culinari.payment.dto.response.toss.detail.*;

import java.math.BigDecimal;
import java.util.List;

public record PaymentTossDto(
        String version,     // 버전 (날짜 기반 버저닝)
        String paymentKey,  // 결제 키 (Max 200)
        String type,        // 결제 타입 정보 [NORMAL(일반 결제), BILLING(자동 결제), BRANDPAY(브랜드페이)]
        String orderId,     // 주문 ID (Length 6 ~ 64)
        String orderName,   // 주문 명 (Max 100) [생수 외 1건]
        String mId,         // 상점 아이디 (Max 14)
        String currency,    // 결제에 사용한 통화 단위 [KRW]
        String method,      // 결제 수단 [카드, 가상계좌, 간편결제, 휴대폰, 계좌이체, 상품권(문화상품권, 도서문화상품권, 게임문화상품권)]
        BigDecimal totalAmount,     // 총 결제 금액
        BigDecimal balanceAmount,   // 취소할 수 있는 금액(잔고)
        String status,      // 결제 처리 상태 [READY, IN_PROGRESS, WAITING_FOR_DEPOSIT, DONE, CANCELED, PARTIAL_CANCELED, ABORTED, EXPIRED]
        String requestedAt, // 결제 시간 정보 [yyyy-MM-dd'T'HH:mm:ss±hh:mm]
        String approvedAt,  // 결제 승인 시간 정보 [yyyy-MM-dd'T'HH:mm:ss±hh:mm]
        Boolean useEscrow,  // 에스크로 사용 여부
        String lastTransactionKey,  // 마지막 거래의 키 값, 한 결제의 승인 거래와 취소 거래를 구분 (Max 64)
        BigDecimal suppliedAmount,  // 공급가액
        BigDecimal vat,     // 부가세
        Boolean cultureExpense,     // 문화비 지출 여부 (계좌이체, 가상계좌에서만 사용)
        BigDecimal taxFreeAmount,   // 결제 중 면세 금액
        BigDecimal taxExemptionAmount,  // 결제 금액 중 과세 제외 금액
        List<PaymentTossCancelDto> cancels,
        Boolean isPartialCancelable,    // 부분 취소 가능 여부
        PaymentTossCardDto card,
        PaymentTossVirtualAccountDto virtualAccount,
        String secret,  // 가상계좌 웹훅이 정상적인 요청인지 검증하는 값 (Max 50)
        PaymentTossMobilePhoneDto mobilePhone,
        PaymentTossGiftCertificateDto giftCertificate,
        PaymentTossTransferDto transfer,
        PaymentTossReceiptDto receipt,
        PaymentTossCheckoutDto checkout,
        PaymentTossEasyPayDto easyPay,
        String country, // 결제한 국가 정보 (ISO-3166의 두 자리 국가 코드 형식)
        PaymentTossFailureDto failure,
        PaymentTossCashReceiptDto cashReceipt,
        PaymentTossDiscountDto discount
) {
}
