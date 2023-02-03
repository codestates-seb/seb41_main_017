package com.codestates.culinari.user.dto.response;

import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.product.dto.ProductImageDto;
import com.codestates.culinari.product.dto.ProductReviewImageDto;
import com.codestates.culinari.product.entitiy.ProductReview;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProfileMyPageReviewExistResponse(
        Long id,
        Long productId,
        List<ProductImageDto> images,
        String name,
        BigDecimal price,
        Integer quantity,
        String content,
        Integer reviewStar,
        List<ProductReviewImageDto> reviewImages
) {

    private static ProfileMyPageReviewExistResponse of(Long id, Long productId, List<ProductImageDto> images, String name, BigDecimal price, Integer quantity,String content,Integer reviewStar,List<ProductReviewImageDto> reviewImages) {
        return new ProfileMyPageReviewExistResponse(id, productId,images,name,price,quantity,content,reviewStar,reviewImages);
    }

    public static ProfileMyPageReviewExistResponse from(ProductReview productReview){
        return ProfileMyPageReviewExistResponse.of(
                productReview.getId(),
                productReview.getProduct().getId(),
                productReview.getProduct().getProductImages()
                        .stream().map(ProductImageDto::from).collect(Collectors.toList()),
                productReview.getProduct().getName(),
                productReview.getOrderDetail().getPrice(),
                productReview.getOrderDetail().getQuantity(),
                productReview.getContent(),
                productReview.getReviewStar(),
                productReview.getProductReviewImages().stream()
                        .map(ProductReviewImageDto::from).collect(Collectors.toList())
        );
    }
}
