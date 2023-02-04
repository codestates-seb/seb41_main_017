package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductReviewImageDto;
import com.codestates.culinari.product.entitiy.ProductReview;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ProductReviewResponse(
        Long id,
        Long productId,
        Long profileId,
        String content,
        Integer reviewStar,
        Long like,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        List<ProductReviewImageDto> productReviewImageDtos

) {
    public static ProductReviewResponse of(Long id, Long productId, Long profileId, String content, Integer reviewStar, Long like, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy,List<ProductReviewImageDto> productReviewImageDtos) {
        return new ProductReviewResponse(id, productId, profileId, content, reviewStar, like, createdAt, modifiedAt, createdBy, modifiedBy,productReviewImageDtos);
    }

    public static ProductReviewResponse from(ProductReview entity) {
        return new ProductReviewResponse(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProfile().getId(),
                entity.getContent(),
                entity.getReviewStar(),
                entity.getProductReviewLike().getLikeNum(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy(),
                entity.getProductReviewImages().stream()
                        .map(ProductReviewImageDto::from)
                        .collect(Collectors.toList())
        );
    }
}
