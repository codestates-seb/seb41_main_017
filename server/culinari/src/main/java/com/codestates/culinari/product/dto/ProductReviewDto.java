package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.entitiy.ProductReviewLike;
import com.codestates.culinari.user.entitiy.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ProductReviewDto(
        Long id,
        Long productId,
        Long profileId,
        Integer reviewStar,
        Long like,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        List<ProductReviewImageDto> productReviewImageDtos
)
{

    public static ProductReviewDto of(Long id, Long productId,Long profileId, Integer reviewStar,Long like, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy,List<ProductReviewImageDto> productReviewImageDtos){
        return new ProductReviewDto(id, productId, profileId,reviewStar, like, content, createdAt, modifiedAt, createdBy, modifiedBy,productReviewImageDtos);

    }

    public static ProductReviewDto of(Long productId,Long profileId, String content,Integer reviewStar){
        return new ProductReviewDto(null, productId, profileId,reviewStar,null,  content,null, null, null, null,null);
    }


    public static ProductReviewDto from(ProductReview entity, ProductReviewLike likeEntity) {
        return new ProductReviewDto(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProfile().getId(),
                entity.getReviewStar(),
                likeEntity.getLikeNum(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy(),
                entity.getProductReviewImages().stream()
                        .map(ProductReviewImageDto::from)
                        .collect(Collectors.toList())

        );
    }

    public static ProductReviewDto from(ProductReview entity) {
        return new ProductReviewDto(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getProfile().getId(),
                entity.getReviewStar(),
                entity.getProductReviewLike().getLikeNum(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy(),
                entity.getProductReviewImages().stream()
                        .map(ProductReviewImageDto::from)
                        .collect(Collectors.toList())

        );
    }

    public ProductReview toEntity(Product product, Profile profile) {
        return ProductReview.of(
                content,
                reviewStar,
                product,
                profile
        );

    }
}
