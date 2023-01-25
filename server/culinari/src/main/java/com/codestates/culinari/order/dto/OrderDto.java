package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.user.entitiy.Profile;

public record OrderDto (
    String address,
    String receiverName,
    String receiverPhoneNumber
){

    public static OrderDto of(String address, String receiverName, String receiverPhoneNumber) {
        return new OrderDto(address, receiverName, receiverPhoneNumber);
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
