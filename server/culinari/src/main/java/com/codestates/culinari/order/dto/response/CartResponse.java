package com.codestates.culinari.order.dto.response;

import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.product.dto.response.ProductResponseToPage;

public record CartResponse(
        Long id,
        Integer quantity,
        ProductResponseToPage product
) {

    public static CartResponse of(Long id, Integer quantity, ProductResponseToPage product) {
        return new CartResponse(id, quantity, product);
    }

    public static CartResponse from(Cart entity) {
        return CartResponse.of(
                entity.getId(),
                entity.getQuantity(),
                ProductResponseToPage.from(entity.getProduct())
        );
    }
}
