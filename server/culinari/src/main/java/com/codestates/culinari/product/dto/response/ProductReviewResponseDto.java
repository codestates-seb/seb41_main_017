package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.entitiy.ProductInquiry;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link ProductInquiry} entity
 */
@Builder
public record ProductReviewResponseDto(
        Long id,
        Long productId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy

) implements Serializable {
    public static ProductReviewResponseDto of(Long id, Long productId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductReviewResponseDto(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ProductReviewResponseDto from(ProductInquiry entity){
        return new ProductReviewResponseDto(
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
}
