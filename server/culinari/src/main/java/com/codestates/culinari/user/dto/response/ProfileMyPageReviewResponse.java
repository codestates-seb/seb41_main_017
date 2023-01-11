package com.codestates.culinari.user.dto.response;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.user.dto.ProfileDto;

import java.util.List;

public record ProfileMyPageReviewResponse(
        Long id,
        List<ProductReviewDto> review
) {

    private static ProfileMyPageReviewResponse of(Long id, List<ProductReviewDto> review) {
        return new ProfileMyPageReviewResponse(id, review);
    }

    public static ProfileMyPageReviewResponse from(ProfileDto dto){
        return ProfileMyPageReviewResponse.of(
                dto.id(),
                dto.productReviewDtos()
        );
    }
}
