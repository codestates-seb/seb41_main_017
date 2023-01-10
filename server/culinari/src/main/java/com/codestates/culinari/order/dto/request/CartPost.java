package com.codestates.culinari.order.dto.request;

import com.codestates.culinari.order.dto.CartDto;
import jakarta.validation.constraints.Positive;

public record CartPost(
        @Positive(message = "해당 값은 1이상이어야 합니다.")
        Long productId,
        @Positive(message = "장바구니 수량은 1 이상 입니다.")
        Integer quantity
) {

    public static CartPost of(Long productId, Integer quantity) {
        return new CartPost(productId, quantity);
    }

    public CartDto toDto() {
        return CartDto.of(
                quantity
        );
    }
}
