package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.user.entitiy.Profile;

public record CartDto (
        Integer quantity
){

    public static CartDto of(Integer quantity) {
        return new CartDto(quantity);
    }

    public Cart toEntity(Profile profile, Product product) {
        return Cart.of(
                quantity,
                profile,
                product
        );
    }
}
