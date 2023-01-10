package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.entitiy.Product;

import java.time.LocalDateTime;

public record OrderDetailDto(
        Long id,
        Integer quantity,
        StatusType statusType,
        ProductDto productDto,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {

    public static OrderDetailDto of(Integer quantity) {
        return new OrderDetailDto(null, quantity, StatusType.STAND_BY, null, null, null, null, null);
    }

    public static OrderDetailDto of(Long id, Integer quantity, StatusType statusType, ProductDto productDto, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new OrderDetailDto(id, quantity, statusType, productDto, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static OrderDetailDto from(OrderDetail entity) {
        return OrderDetailDto.of(
                entity.getId(),
                entity.getQuantity(),
                entity.getStatusType(),
                ProductDto.from(entity.getProduct()),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
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
