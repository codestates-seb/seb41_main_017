package com.codestates.culinari.order.dto.response;

import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.dto.OrderDetailDto;
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

    public static OrderDetailResponse from(OrderDetailDto dto) {
        return OrderDetailResponse.of(
                dto.id(),
                dto.quantity(),
                dto.statusType(),
                ProductResponseToPage.from(dto.productDto())
        );
    }
}
