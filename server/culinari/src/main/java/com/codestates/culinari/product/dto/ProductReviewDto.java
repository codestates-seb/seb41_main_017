package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductReview;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.ProductReview} entity
 */
public record ProductReviewDto(
        Long id,
        Long productId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) implements Serializable {
    public static ProductReviewDto of(Long id, Long productId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductReviewDto(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy);

    }

    public static ProductReviewDto of(Long productId, String title,String content){
        return new ProductReviewDto(null, productId, title, content, null, null, null, null);
    }


    public static ProductReviewDto from(ProductReview entity) {
        return new ProductReviewDto(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }



    public ProductReview toEntity(Product product) {
        return ProductReview.of(
                title,
                content,
                product
        );

    }
}