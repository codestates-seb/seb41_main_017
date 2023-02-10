package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.CategoryDetail;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductImage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ProductDto(
        Long id,
        String categoryDetailCode,
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
        List<ProductImageDto> productImageDtos
)
{
    public static ProductDto of(
            Long id,
            String categoryDetailCode,
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
            List<ProductImageDto> productImageDtos
    ){
        return new ProductDto(id, categoryDetailCode, name, content, price, shipping, brand, seller, packaging, unit, weight, countryOfOrigin, allergyInfo, createdAt, modifiedAt, createdBy, modifiedBy,productImageDtos);
    }

    public static ProductDto of(Long id, String name, BigDecimal price,List<ProductImageDto> productImageDtos){
        return new ProductDto(id, null, name, null, price, null, null, null, null, null, null, null, null, null, null, null, null,productImageDtos);
    }
    public static ProductDto from(Product entity){
        return new ProductDto(
                entity.getId(),
                entity.getCategoryDetail().getCategoryDetailCode(),
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
                entity.getModifiedBy(),
                entity.getProductImages().stream()
                        .map(ProductImageDto::from)
                        .collect(Collectors.toList())
        );
    }

    public Product toEntity(CategoryDetail categoryDetail){
        return Product.of(
                categoryDetail,
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
                allergyInfo
        );
    }
}
