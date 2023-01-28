package com.codestates.culinari.payment.dto.request;

import com.codestates.culinari.payment.dto.RefundDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RefundRequest (
        @NotNull(message = "해당 값은 필수입니다.")
        @Size(min = 1, message = "최소 1개 이상의 상품을 주문해야합니다.")
        List<@Positive(message = "해당 값은 1이상이어야 합니다.") Long> orderDetailIds,
        @NotBlank(message = "결제 키는 빈칸일 수 없습니다.")
        @Length(max = 200, message = "결재 키는 200자 이하입니다.")
        String paymentKey,
        @NotBlank(message = "환불 사유는 빈칸일 수 없습니다.")
        String cancelReason
) {

    public static RefundRequest of(List<Long> orderDetailIds, String paymentKey, String cancelReason) {
        return new RefundRequest(orderDetailIds, paymentKey, cancelReason);
    }

    public RefundDto toDto() {
        return RefundDto.of(paymentKey, cancelReason);
    }
}
