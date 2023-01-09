package com.codestates.culinari.product.dto.request;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.user.dto.ProfileDto;
import jakarta.validation.constraints.NotBlank;

public record ProductInquiryRequest(
        Long productId,
        @NotBlank String title,
        @NotBlank String content
) {
    public static ProductInquiryRequest of(Long productId, String title, String content){
        return new ProductInquiryRequest(productId,title,content);
    }

    public ProductInquiryDto toDto(ProfileDto profile){
        return ProductInquiryDto.of(
                productId,
                profile,
                title,
                content
        );
    }
}
