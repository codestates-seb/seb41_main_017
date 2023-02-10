package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.product.entitiy.Product;

public record OrderDetailDto(
        Integer quantity,
        StatusType statusType
) {

    public static OrderDetailDto of(Integer quantity) {
        return new OrderDetailDto(quantity, StatusType.ORDER_RECEIVED);
    }

    public OrderDetail toEntity(Orders orders, Product product) {
        return OrderDetail.of(
                quantity,
                statusType,
                orders,
                product
        );
    }
}
