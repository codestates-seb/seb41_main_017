package com.codestates.culinari.payment.entity;

import com.codestates.culinari.order.entitiy.OrderDetail;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cancelReason;

    @Column(nullable = false)
    private String paymentKey;

    @OneToOne
    private OrderDetail orderDetail;

    private Refund(String cancelReason, String paymentKey, OrderDetail orderDetail) {
        this.cancelReason = cancelReason;
        this.paymentKey = paymentKey;
        this.orderDetail = orderDetail;
    }

    public static Refund of(String cancelReason, String paymentKey, OrderDetail orderDetail) {
        return new Refund(cancelReason, paymentKey, orderDetail);
    }
}
