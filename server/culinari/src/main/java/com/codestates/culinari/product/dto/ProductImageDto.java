package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductImage;

public record ProductImageDto(
        Long id,
        String imgUrl
) {
    public static ProductImageDto of (Long id, String imgUrl){

        return new ProductImageDto(id, imgUrl);
    }
    public static ProductImageDto from(ProductImage entity){
        return new ProductImageDto(
                entity.getId(),
                entity.getImgUrl()
        );
    }
    public ProductImage toEntity(Product product){
        return ProductImage.of(
                imgUrl,
                product
        );
    }
}
