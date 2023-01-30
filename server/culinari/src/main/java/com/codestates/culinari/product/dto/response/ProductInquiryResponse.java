package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.entitiy.ProductInquiry;

import java.time.LocalDateTime;

public record ProductInquiryResponse(
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
    public static ProductInquiryResponse of(Long id, Long productId, Long profileId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy){
        return new ProductInquiryResponse(id, productId, profileId, title, content, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ProductInquiryResponse from(ProductInquiry entity){
        return new ProductInquiryResponse(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProfile().getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }
}
