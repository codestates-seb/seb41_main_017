package com.codestates.culinari.order.dto.request;

import com.codestates.culinari.order.dto.CartDto;
import jakarta.validation.constraints.Positive;

public record CartPatch(
        @Positive(message = "장바구니 수량은 1 이상 입니다.")
        Integer quantity
) {

    public static CartPatch of(Integer quantity) {
        return new CartPatch(quantity);
    }

    public CartDto toDto() {
        return CartDto.of(
                quantity
        );
    }
}
