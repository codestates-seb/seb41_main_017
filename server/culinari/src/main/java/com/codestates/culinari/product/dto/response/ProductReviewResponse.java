package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.dto.ProductReviewImageDto;
import com.codestates.culinari.product.entitiy.ProductReview;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductReviewResponse(
        Long id,
        Long productId,
        Long profileId,
        String title,
        String content,
        Integer reviewStar,
        Long like,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        List<ProductReviewImageDto> productReviewImageDtos

) {
    public static ProductReviewResponse of(Long id, Long productId, Long profileId, String title, String content, Integer reviewStar, Long like, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy,List<ProductReviewImageDto> productReviewImageDtos) {
        return new ProductReviewResponse(id, productId, profileId, title, content, reviewStar, like, createdAt, modifiedAt, createdBy, modifiedBy,productReviewImageDtos);
    }

    public static ProductReviewResponse from(ProductReviewDto dto) {
        return new ProductReviewResponse(
                dto.id(),
                dto.productId(),
                dto.profileId(),
                dto.title(),
                dto.content(),
                dto.reviewStar(),
                dto.like(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.createdBy(),
                dto.modifiedBy(),
                dto.productReviewImageDtos()
        );
    }
}
