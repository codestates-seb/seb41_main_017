package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseWithCustomerService(
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
        List<ProductInquiryDto> productInquiryDtos,
        List<ProductReviewDto> productReviewDtos

)
{

    public static ProductResponseWithCustomerService of(
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
            List<ProductInquiryDto> productInquiryDtos,
            List<ProductReviewDto> productReviewDtos
    ) {
        return new ProductResponseWithCustomerService(
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

    public static ProductResponseWithCustomerService from(ProductDto dto){
        return new ProductResponseWithCustomerService(
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
