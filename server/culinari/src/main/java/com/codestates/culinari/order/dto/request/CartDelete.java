package com.codestates.culinari.order.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CartDelete(
        @NotNull(message = "해당 값은 필수입니다.")
        @Size(min = 1, message = "최소 1개 이상의 상품을 주문해야합니다.")
        List<@Positive(message = "해당 값은 1이상이어야 합니다.") Long> cartIds
) {

    public static CartDelete of(List<Long> cartIds) {
        return new CartDelete(cartIds);
    }
}