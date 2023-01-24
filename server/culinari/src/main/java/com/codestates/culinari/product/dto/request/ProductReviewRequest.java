package com.codestates.culinari.product.dto.request;

import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.user.dto.ProfileDto;
import jakarta.validation.constraints.NotBlank;

public record ProductReviewRequest(
        Long productId,
        @NotBlank(message = "제목 입력은 필수입니다.") String title,
        @NotBlank(message = "내용 입력은 필수입니다.") String content,
        @NotBlank(message = "별점은 필수입니다.") ProductReview.ReviewStar reviewStar

) {
    public static ProductReviewRequest of(Long productId, String title, String content, ProductReview.ReviewStar reviewStar ) {
        return new ProductReviewRequest(productId, title, content, reviewStar);
    }


    public ProductReviewDto toDto(ProfileDto profile) {
        return ProductReviewDto.of(
                productId,
                profile.id(),
                title,
                content,
                reviewStar
        );
    }
}
