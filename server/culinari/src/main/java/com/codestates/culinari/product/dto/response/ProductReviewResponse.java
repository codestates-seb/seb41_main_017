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
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        String name,
        String emial

)
{
    public static ProductReviewResponse of(Long id, Long productId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, String name, String email){
        return new ProductReviewResponse(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy, name, email);
    }

    public static ProductReviewResponse from(ProductReviewDto dto){
        return new ProductReviewResponse(
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
