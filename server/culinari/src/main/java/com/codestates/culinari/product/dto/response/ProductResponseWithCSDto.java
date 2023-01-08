package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductReviewDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.Product} entity
 */
public record ProductResponseWithCSDto(
        Long id,
        String name,
        String content,
        BigDecimal price,
        String shipping,
        String brand,
        String seller,
        String packaging,
        String unit,
        String weight,
        String countryOfOrigin,
        String allergyInfo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        List<ProductInquiryResponseDto> productInquiryDtos,
        List<ProductReviewDto> productReviewDtos

        ) implements Serializable {

    public static ProductResponseWithCSDto of(
            Long id,
            String name,
            String content,
            BigDecimal price,
            String shipping,
            String brand,
            String seller,
            String packaging,
            String unit,
            String weight,
            String countryOfOrigin,
            String allergyInfo,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            String createdBy,
            String modifiedBy,
            List<ProductInquiryResponseDto> productInquiryDtos,
            List<ProductReviewDto> productReviewDtos
    ) {
        return new ProductResponseWithCSDto(
                id,
                name,
                content,
                price,
                shipping,
                brand,
                seller,
                packaging,
                unit,
                weight,
                countryOfOrigin,
                allergyInfo,
                createdAt,
                modifiedAt,
                createdBy,
                modifiedBy,
                productInquiryDtos,
                productReviewDtos
        );
    }

    public static ProductResponseWithCSDto from(ProductDto dto){
        return new ProductResponseWithCSDto(
                dto.id(),
                dto.name(),
                dto.content(),
                dto.price(),
                dto.shipping(),
                dto.brand(),
                dto.seller(),
                dto.packaging(),
                dto.unit(),
                dto.weight(),
                dto.countryOfOrigin(),
                dto.allergyInfo(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.createdBy(),
                dto.modifiedBy(),
                dto.productInquiryDtos(),
                dto.productReviewDtos()
        );
    }
}