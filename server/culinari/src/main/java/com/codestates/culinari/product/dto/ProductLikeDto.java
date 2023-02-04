package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductLike;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProductLikeDto(
        Long id,
        Long productId,
        String name,
        BigDecimal price,
        List<ProductImageDto> productImageDtos
){
    public static ProductLikeDto of(
            Long id,
            Long productId,
            String name,
            BigDecimal price,
            List<ProductImageDto> productImageDtos
    )
    {
        return new ProductLikeDto(id,productId,name,price,productImageDtos);
    }

    public static ProductLikeDto from(ProductLike entity){
        return new ProductLikeDto(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProduct().getName(),
                entity.getProduct().getPrice(),
                entity.getProduct().getProductImages().stream()
                        .map(ProductImageDto::from)
                        .collect(Collectors.toList())
        );
    }

    public ProductLike toEntity(Profile profile, Product product){
        return ProductLike.of(
                profile,
                product
        );
    }
}
