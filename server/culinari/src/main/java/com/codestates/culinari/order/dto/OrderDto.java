package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.entitiy.Profile;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto (
    Long id,
    String address,
    String receiverName,
    String receiverPhoneNumber,
    ProfileDto profileDto,
    List<OrderDetailDto> orderDetailDtos,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt,
    String createdBy,
    String modifiedBy
){

    public static OrderDto of(String address, String receiverName, String receiverPhoneNumber) {
        return new OrderDto(null, address, receiverName, receiverPhoneNumber, null, null, null, null, null, null);
    }

    public static OrderDto of(Long id, String address, String receiverName, String receiverPhoneNumber, ProfileDto profileDto, List<OrderDetailDto> orderDetailDtos, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new OrderDto(id, address, receiverName, receiverPhoneNumber, profileDto, orderDetailDtos, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static OrderDto from(Orders entity) {
        return OrderDto.of(
                entity.getId(),
                entity.getAddress(),
                entity.getReceiverName(),
                entity.getReceiverPhoneNumber(),
                ProfileDto.from(entity.getProfile()),
                entity.getOrderDetails().stream()
                        .map(OrderDetailDto::from)
                        .toList(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }

    public Orders toEntity(Profile profile) {
        return Orders.of(
                address,
                receiverName,
                receiverPhoneNumber,
                profile
        );
    }
}
