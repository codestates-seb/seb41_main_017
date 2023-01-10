package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.entitiy.ProductInquiry;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ProductInquiryResponse(
        Long id,
        Long productId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        String name,
        String email

)
{
    public static ProductInquiryResponse of(Long id, Long productId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, String name, String email){
        return new ProductInquiryResponse(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy, name, email);
    }

    public static ProductInquiryResponse from(ProductInquiryDto dto){
        return new ProductInquiryResponse(
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
