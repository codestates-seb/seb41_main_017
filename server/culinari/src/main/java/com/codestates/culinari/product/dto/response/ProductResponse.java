package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductImageDto;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ProductResponse(
        Long id,
        List<ProductImageDto> productImageDtos,
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
        String modifiedBy

)
{

    public static ProductResponse of(
            Long id,
            List<ProductImageDto> productImageDtos,
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
            String modifiedBy
    ) {
        return new ProductResponse(
                id,
                productImageDtos,
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
                modifiedBy
        );
    }

    public static ProductResponse from(Product entity){
        return new ProductResponse(
                entity.getId(),
                entity.getProductImages().stream()
                        .map(ProductImageDto::from)
                        .collect(Collectors.toList()),
                entity.getName(),
                entity.getContent(),
                entity.getPrice(),
                entity.getShipping(),
                entity.getBrand(),
                entity.getSeller(),
                entity.getPackaging(),
                entity.getUnit(),
                entity.getWeight(),
                entity.getCountryOfOrigin(),
                entity.getAllergyInfo(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }
}
