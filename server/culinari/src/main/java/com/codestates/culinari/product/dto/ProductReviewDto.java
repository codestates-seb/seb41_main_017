package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.entitiy.ProductReviewLike;
import com.codestates.culinari.user.entitiy.Profile;

import java.time.LocalDateTime;

public record ProductReviewDto(
        Long id,
        Long productId,
        Long profileId,
        ProductReview.ReviewStar reviewStar,
        Long like,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
)
{

    public static ProductReviewDto of(Long id, Long productId,Long profileId,ProductReview.ReviewStar reviewStar,Long like, String title, String content,  LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductReviewDto(id, productId, profileId,reviewStar, like, title, content, createdAt, modifiedAt, createdBy, modifiedBy);

    }

    public static ProductReviewDto of(Long productId,Long profileId, String title,String content,ProductReview.ReviewStar reviewStar){
        return new ProductReviewDto(null, productId, profileId,reviewStar,null, title, content, null, null, null, null);
    }


    public static ProductReviewDto from(ProductReview entity, ProductReviewLike likeEntity) {
        return new ProductReviewDto(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProfile().getId(),
                entity.getReviewStar(),
                likeEntity.getLikeNum(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }

    public static ProductReviewDto from(ProductReview entity) {
        return new ProductReviewDto(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProfile().getId(),
                entity.getReviewStar(),
                entity.getProductReviewLike().getLikeNum(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }

    public ProductReview toEntity(Product product, Profile profile) {
        return ProductReview.of(
                title,
                content,
                reviewStar,
                product,
                profile
                );

    }
}
