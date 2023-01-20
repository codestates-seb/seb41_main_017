package com.codestates.culinari.payment.dto;

import com.codestates.culinari.order.dto.OrderDto;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;

public record PaymentDto(
        PayType payType,
        BigDecimal amount,
        OrderDto orderDto,
        String productName
) {

    public static PaymentDto of(PayType payType, BigDecimal amount, OrderDto orderDto, String productName) {
        return new PaymentDto(payType, amount, orderDto, productName);
    }

    public static PaymentDto from(Payment entity) {
        return PaymentDto.of(
                entity.getPayType(),
                entity.getAmount(),
                OrderDto.from(entity.getOrder()),
                entity.getProductName()
        );
    }

    public static PaymentDto from(Orders order, PayType payType) {
        return PaymentDto.of(
                payType,
                order.getOrderDetails().stream()
                        .map(OrderDetail::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                OrderDto.from(order),
                order.getOrderDetails().size() == 1
                        ? String.format("%s", order.getOrderDetails().get(0).getProduct().getName())
                        : String.format("%s 외 %d개", order.getOrderDetails().get(0).getProduct().getName(), order.getOrderDetails().size() - 1)
        );
    }

    public Payment toEntity(Orders order, Profile profile) {
        return Payment.of(
                payType,
                amount,
                order,
                productName,
                profile
        );
    }
}
