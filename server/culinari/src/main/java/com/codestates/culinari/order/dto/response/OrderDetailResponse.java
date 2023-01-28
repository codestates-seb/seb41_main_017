package com.codestates.culinari.order.dto.response;

import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.product.dto.response.ProductResponseToPage;

public record OrderDetailResponse(
        Long id,
        Integer quantity,
        StatusType status,
        ProductResponseToPage product
) {

    public static OrderDetailResponse of(Long id, Integer quantity, StatusType status, ProductResponseToPage product) {
        return new OrderDetailResponse(id, quantity, status, product);
    }

    public static OrderDetailResponse from(OrderDetail entity) {
        return OrderDetailResponse.of(
                entity.getId(),
                entity.getQuantity(),
                entity.getStatusType(),
                ProductResponseToPage.from(entity.getProduct())
        );
    }
}
