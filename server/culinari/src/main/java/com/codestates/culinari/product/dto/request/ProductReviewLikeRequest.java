package com.codestates.culinari.product.dto.request;

import com.codestates.culinari.product.dto.ProductReviewLikeDto;
import com.codestates.culinari.user.dto.ProfileDto;
import jakarta.validation.constraints.NotNull;

public record ProductReviewLikeRequest(

        Long productReviewId,
        Long profileId,
        @NotNull Long like
)
{
    public static ProductReviewLikeRequest of(Long productReviewId, Long profileId, Long like){
        return new ProductReviewLikeRequest(productReviewId, profileId, like);
    }

    public ProductReviewLikeDto toDto(ProfileDto profile){
        return ProductReviewLikeDto.of(
                profile.id(),
                like
        );
    }
}
