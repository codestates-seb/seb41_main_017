package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.entitiy.ProductReviewLike;

import java.time.LocalDateTime;

public record ProductReviewLikeDto(
        Long id,
        Long productReviewId,
        Long likeNum,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static ProductReviewLikeDto of(Long id,Long productReviewId, Long likeNum, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductReviewLikeDto(id, productReviewId, likeNum, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ProductReviewLikeDto of(Long productReviewId, Long likeNum){
        return new ProductReviewLikeDto(null, productReviewId, likeNum, null, null, null, null);
    }

    public static ProductReviewLikeDto from(ProductReviewLike entity){
        return new ProductReviewLikeDto(
                entity.getId(),
                entity.getProductReview().getId(),
                entity.getLikeNum(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }
    public ProductReviewLike toEntity(ProductReview productReview){
        return ProductReviewLike.of(
                likeNum,
                productReview
        );
    }
}
