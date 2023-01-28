package com.codestates.culinari.order.dto.request;

import com.codestates.culinari.order.dto.CartDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CartPost(
        @NotNull(message = "해당 값은 필수입니다.")
        @Size(min = 1, message = "최소 1개 이상의 상품을 주문해야합니다.")
        List<@Valid CartInfo> cartItems
) {

    public record CartInfo(
            @NotNull(message = "해당 값은 필수입니다.")
            @Positive(message = "해당 값은 1이상이어야 합니다.")
            Long productId,
            @NotNull(message = "해당 값은 필수입니다.")
            @Positive(message = "장바구니 수량은 1 이상 입니다.")
            Integer quantity
    ) {
        public CartDto toDto() {
            return CartDto.of(
                    quantity
            );
        }
    }
}
