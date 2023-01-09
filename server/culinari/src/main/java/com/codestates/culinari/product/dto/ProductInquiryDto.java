package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.entitiy.Profile;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.ProductInquiry} entity
 */
public record ProductInquiryDto(
        Long id,
        Long productId,
        ProfileDto profileDto,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy

) implements Serializable {
    public static ProductInquiryDto of(Long id, Long productId,ProfileDto profileDto, String title,String content,LocalDateTime createdAt, LocalDateTime modifiedAt,String createdBy, String modifiedBy){
        return new ProductInquiryDto(id, productId, profileDto, title, content, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ProductInquiryDto of(Long productId,ProfileDto profileDto, String title,String content){
        return new ProductInquiryDto(null, productId, profileDto, title, content, null, null, null, null);
    }

    public static ProductInquiryDto from(ProductInquiry entity){
        return new ProductInquiryDto(
                entity.getId(),
                entity.getProduct().getId(),
                ProfileDto.from(entity.getProfile()),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }
    public ProductInquiry toEntity(Product product, Profile profile){
        return ProductInquiry.of(
                title,
                content,
                product,
                profile
        );
    }
}
