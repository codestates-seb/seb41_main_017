package com.codestates.culinari.product.dto.request;

import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.user.dto.ProfileDto;
import jakarta.validation.constraints.NotBlank;

public record ProductReviewRequest(
        Long productId,
        @NotBlank(message = "제목 입력은 필수입니다.") String title,
        @NotBlank(message = "내용 입력은 필수입니다.") String content

) {
    public static ProductReviewRequest of(Long productId, String title, String content){
        return new ProductReviewRequest(productId, title,content);
    }


    public ProductReviewDto toDto(ProfileDto profile){
        return ProductReviewDto.of(
                productId,
                profile.id(),
                title,
                content
        );
    }
}
