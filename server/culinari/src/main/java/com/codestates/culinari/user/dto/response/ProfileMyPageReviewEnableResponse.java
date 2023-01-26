package com.codestates.culinari.user.dto.response;

import com.codestates.culinari.order.dto.OrderDetailDto;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.product.dto.ProductImageDto;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductImage;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProfileMyPageReviewEnableResponse(
        Long id,
        Long productId,
        List<ProductImageDto> images,
        String name,
        BigDecimal price,
        Integer quantity
) {

    private static ProfileMyPageReviewEnableResponse of(Long id, Long productId, List<ProductImageDto> images, String name, BigDecimal price,Integer quantity) {
        return new ProfileMyPageReviewEnableResponse(id, productId,images,name,price,quantity);
    }

    public static ProfileMyPageReviewEnableResponse from(OrderDetail orderDetail){
        return ProfileMyPageReviewEnableResponse.of(
                orderDetail.getId(),
                orderDetail.getProduct().getId(),
                orderDetail.getProduct().getProductImages()
                        .stream().map(ProductImageDto::from).collect(Collectors.toList()),
                orderDetail.getProduct().getName(),
                orderDetail.getPrice(),
                orderDetail.getQuantity()
        );
    }
}
