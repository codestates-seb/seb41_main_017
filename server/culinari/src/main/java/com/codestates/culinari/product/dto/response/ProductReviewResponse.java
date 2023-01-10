package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ProductReviewResponse(
        Long id,
        Long productId,
        Long profileId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
)
{
    public static ProductReviewResponse of(Long id, Long productId, Long profileId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductReviewResponse(id, productId, profileId, title, content, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ProductReviewResponse from(ProductReviewDto dto){
        return new ProductReviewResponse(
              dto.id(),
                dto.productId(),
                dto.profileId(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.createdBy(),
                dto.modifiedBy()
        );
    }
}
