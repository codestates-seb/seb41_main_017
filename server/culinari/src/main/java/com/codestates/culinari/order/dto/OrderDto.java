package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.user.entitiy.Profile;

public record OrderDto (
    String id,
    String address,
    String receiverName,
    String receiverPhoneNumber
){

    public static OrderDto of(String id, String address, String receiverName, String receiverPhoneNumber) {
        return new OrderDto(id, address, receiverName, receiverPhoneNumber);
    }

    public Orders toEntity(Profile profile) {
        return Orders.of(
                id,
                address,
                receiverName,
                receiverPhoneNumber,
                profile
        );
    }
}
