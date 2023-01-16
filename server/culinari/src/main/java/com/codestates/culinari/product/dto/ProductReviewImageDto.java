package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.ProductReviewImage;
import lombok.Builder;

@Builder
public record ProductReviewImageDto(
        Long id,
        Long productReviewId,
        String originImageName,
        String storeImageName
){
    public static ProductReviewImageDto of(Long id,Long productReviewId, String originImageName, String storeImageName){
        return new ProductReviewImageDto(id,productReviewId, originImageName, storeImageName);
    }

    public static ProductReviewImageDto from(ProductReviewImage entity){
        return new ProductReviewImageDto(
                entity.getId(),
                entity.getProductReview().getId(),
                entity.getOriginImageName(),
                entity.getStoreImageName()
        );
    }
    public ProductReviewImage toEntity(){
        return ProductReviewImage.of(
                originImageName,
                storeImageName

        );
    }
}