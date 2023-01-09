package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.entitiy.Profile;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ProductReviewDto(
        Long id,
        Long productId,
        ProfileDto profileDto,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
)
{

    public static ProductReviewDto of(Long id, Long productId,ProfileDto profileDto, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductReviewDto(id, productId, profileDto, title, content, createdAt, modifiedAt, createdBy, modifiedBy);

    }

    public static ProductReviewDto of(Long productId,ProfileDto profileDto, String title,String content){
        return new ProductReviewDto(null, productId, profileDto, title, content, null, null, null, null);
    }


    public static ProductReviewDto from(ProductReview entity) {
        return new ProductReviewDto(
                entity.getId(),
                entity.getProduct().getId(),
                ProfileDto.from(entity.getProfile()),
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
                product,
                profile
                );

    }
}
