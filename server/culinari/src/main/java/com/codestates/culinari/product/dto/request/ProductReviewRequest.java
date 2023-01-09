package com.codestates.culinari.product.dto.request;

import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.Product;
import jakarta.validation.constraints.NotBlank;

public record ProductReviewRequest(
        @NotBlank String title,
        @NotBlank String content
) {
    public static ProductReviewRequest of(String title, String content){
        return new ProductReviewRequest( title,content);
    }


    public ProductReviewDto toDto(Product product){
        return ProductReviewDto.of(
                product.getId(),
                title,
                content
        );
    }
}
