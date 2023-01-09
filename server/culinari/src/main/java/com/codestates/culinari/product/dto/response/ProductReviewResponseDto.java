package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.product.entitiy.ProductReview;
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
        String modifiedBy,
        String name,
        String emial

) implements Serializable {
    public static ProductReviewResponseDto of(Long id, Long productId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy,String name, String email){
        return new ProductReviewResponseDto(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy, name, email);
    }

    public static ProductReviewResponseDto from(ProductReviewDto dto){
        return new ProductReviewResponseDto(
              dto.id(),
                dto.productId(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.createdBy(),
                dto.modifiedBy(),
                dto.profileDto().name(),
                dto.profileDto().email()
        );
    }
}
