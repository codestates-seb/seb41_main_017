package com.codestates.culinari.payment.dto;

import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;

public record PaymentDto(
        PayType payType
) {

    public static PaymentDto of(PayType payType) {
        return new PaymentDto(payType);
    }

    public Payment toEntity(Orders order, Profile profile) {
        return Payment.of(
                payType,
                order.getOrderDetails().stream()
                        .map(OrderDetail::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                order.getOrderDetails().size() == 1
                        ? String.format("%s", order.getOrderDetails().get(0).getProduct().getName())
                        : String.format("%s 외 %d개", order.getOrderDetails().get(0).getProduct().getName(), order.getOrderDetails().size() - 1),
                order,
                profile
        );
    }
}
