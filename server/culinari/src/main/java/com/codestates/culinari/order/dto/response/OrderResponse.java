package com.codestates.culinari.order.dto.response;

import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        String address,
        String receiverName,
        String receiverPhoneNumber,
        StatusType status,
        LocalDateTime createdAt,
        List<OrderDetailResponse> orderDetails
) {

    public static OrderResponse of(
            String id, String address, String receiverName, String receiverPhoneNumber,
            StatusType status, LocalDateTime createdAt, List<OrderDetailResponse> orderDetails
    ) {
        return new OrderResponse(id, address, receiverName, receiverPhoneNumber, status, createdAt, orderDetails);
    }

    public static OrderResponse from(Orders entity) {
        List<OrderDetail> orderDetails = entity.getOrderDetails();

        return OrderResponse.of(
                entity.getId(),
                entity.getAddress(),
                entity.getReceiverName(),
                entity.getReceiverPhoneNumber(),
                orderDetails.stream()
                        .map(OrderDetail::getStatusType)
                        .min(Enum::compareTo)
                        .orElseThrow(NullPointerException::new),
                entity.getCreatedAt(),
                orderDetails.stream()
                        .map(OrderDetailResponse::from)
                        .toList()
        );
    }
}
