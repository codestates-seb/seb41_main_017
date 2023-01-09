package com.codestates.culinari.product.dto.request;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.entitiy.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductInquiryRequest(
        @NotBlank String title,
        @NotBlank String content
) {
    public static ProductInquiryRequest of( String title, String content){
        return new ProductInquiryRequest( title,content);
    }


    public ProductInquiryDto toDto(Product product){
        return ProductInquiryDto.of(
                product.getId(),
                title,
                content
        );
    }
}
