package com.codestates.culinari.order.dto.response;

import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.order.dto.OrderDetailDto;
import com.codestates.culinari.order.dto.OrderDto;

import java.util.List;

public record OrderResponse(
        Long id,
        String address,
        String receiverName,
        String receiverPhoneNumber,
        StatusType status,
        List<OrderDetailResponse> orderDetails
) {

    public static OrderResponse of(Long id, String address, String receiverName, String receiverPhoneNumber, StatusType status, List<OrderDetailResponse> orderDetails) {
        return new OrderResponse(id, address, receiverName, receiverPhoneNumber, status, orderDetails);
    }

    public static OrderResponse from(OrderDto dto) {
        List<OrderDetailDto> orderDetailDtos = dto.orderDetailDtos();

        return OrderResponse.of(
                dto.id(),
                dto.address(),
                dto.receiverName(),
                dto.receiverPhoneNumber(),
                orderDetailDtos.stream()
                        .map(OrderDetailDto::statusType)
                        .min(Enum::compareTo)
                        .orElseThrow(NullPointerException::new),
                orderDetailDtos.stream()
                        .map(OrderDetailResponse::from)
                        .toList()
        );
    }
}
