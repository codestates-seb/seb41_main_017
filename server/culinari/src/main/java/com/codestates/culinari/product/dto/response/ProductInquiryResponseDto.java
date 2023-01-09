package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.entitiy.ProductInquiry;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link ProductInquiry} entity
 */
public record ProductInquiryResponseDto(
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

) implements Serializable {
    public static ProductInquiryResponseDto of(Long id, Long productId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, String name, String email){
        return new ProductInquiryResponseDto(id, productId, title, content, createdAt, modifiedAt, createdBy, modifiedBy, name, email);
    }

    public static ProductInquiryResponseDto from(ProductInquiryDto dto){
        return new ProductInquiryResponseDto(
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
