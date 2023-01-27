package com.codestates.culinari.payment.dto.request;

import com.codestates.culinari.payment.dto.RefundDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record RefundRequest (
        @NotNull(message = "해당 값은 필수입니다.")
        @Positive(message = "해당 값은 1이상이어야 합니다.")
        Long orderDetailId,
        @NotBlank(message = "결제 키는 빈칸일 수 없습니다.")
        @Length(max = 200, message = "결재 키는 200자 이하입니다.")
        String paymentKey,
        @NotBlank(message = "환불 사유는 빈칸일 수 없습니다.")
        String cancelReason
) {

    public static RefundRequest of(Long orderDetailId, String paymentKey, String cancelReason) {
        return new RefundRequest(orderDetailId, paymentKey, cancelReason);
    }

    public RefundDto toDto() {
        return RefundDto.of(
                orderDetailId,
                paymentKey,
                cancelReason
        );
    }
}