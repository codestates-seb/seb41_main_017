package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.entitiy.ProductInquiry;

import java.io.Serializable;
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

    public static ProductInquiryResponse from(ProductInquiryDto dto){
        return new ProductInquiryResponse(
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
