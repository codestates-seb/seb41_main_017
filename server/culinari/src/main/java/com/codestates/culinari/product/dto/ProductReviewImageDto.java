package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.entitiy.ProductReviewImage;
import lombok.Builder;

@Builder
public record ProductReviewImageDto(
        Long id,
        Long productReviewId,
        String imgUrl
){
    public static ProductReviewImageDto of(Long id,Long productReviewId,  String imgUrl){
        return new ProductReviewImageDto(id,productReviewId,imgUrl);
    }

    public static ProductReviewImageDto from(ProductReviewImage entity){
        return new ProductReviewImageDto(
                entity.getId(),
                entity.getProductReview().getId(),
                entity.getImgUrl()
        );
    }
    public ProductReviewImage toEntity(ProductReview productReview){
        return ProductReviewImage.of(
                imgUrl,
                productReview
        );
    }
}