package com.codestates.culinari.payment.dto.request;

import com.codestates.culinari.payment.constant.PayType;
import jakarta.validation.constraints.*;

import java.util.List;

public record PaymentRequest(
        @NotNull(message = "결제 수단은 필수입니다.")
        PayType payType,
        @NotNull(message = "해당 값은 필수입니다.")
        @Size(min = 1, message = "최소 1개 이상의 상품을 주문해야합니다.")
        List<@Positive(message = "해당 값은 1이상이어야 합니다.") Long> productIds,
        @NotBlank(message = "주소 입력은 필수입니다.")
        String address,
        @NotBlank(message = "수령자 입력은 필수입니다.")
        String receiverName,
        @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "전화번호의 형식은 000-0000-0000 입니다.")
        String receiverPhoneNumber

) {
    public static PaymentRequest of(PayType payType, List<Long> productIds, String address, String receiverName, String receiverPhoneNumber) {
        return new PaymentRequest(payType, productIds, address, receiverName, receiverPhoneNumber);
    }
}
