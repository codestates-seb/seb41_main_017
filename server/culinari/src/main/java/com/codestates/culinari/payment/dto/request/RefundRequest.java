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
        @NotBlank(message = "환불 사유는 빈칸일 수 없습니다.")
        String cancelReason
) {

    public static RefundRequest of(List<Long> orderDetailIds, String paymentKey, String cancelReason) {
        return new RefundRequest(orderDetailIds, cancelReason);
    }

    public RefundDto toDto() {
        return RefundDto.of(cancelReason);
    }
}
