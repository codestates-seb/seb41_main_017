package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductLike;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;

public record ProductLikeDto(
        Long id,
        String name,
        BigDecimal price

){
    public static ProductLikeDto of(
            Long id,
            String name,
            BigDecimal price
    )
    {
        return new ProductLikeDto(id,name,price);
    }

    public static ProductLikeDto from(ProductLike entity){
        return new ProductLikeDto(
                entity.getId(),
                entity.getProduct().getName(),
                entity.getProduct().getPrice()
        );
    }

    public ProductLike toEntity(Profile profile, Product product){
        return ProductLike.of(
                profile,
                product
        );
    }
}
