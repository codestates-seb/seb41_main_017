package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.ProductInquiry;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.ProductInquiry} entity
 */
@Builder
public record ProductInquiryDto(
        Long id,
        Long productId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy

) implements Serializable {
    public static ProductInquiryDto of(Long id, Long productId,String title,String content,LocalDateTime createdAt, LocalDateTime modifiedAt,String createdBy, String modifiedBy){
        return new ProductInquiryDto(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ProductInquiryDto from(ProductInquiry entity){
        return new ProductInquiryDto(
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
