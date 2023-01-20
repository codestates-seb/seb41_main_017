package com.codestates.culinari.payment.entity;

import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private PayType payType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String productName;

    @Setter
    @Column
    private String paymentKey;

    @Setter
    @Column
    private Boolean paySuccessTf;

    @Setter
    @Column
    private String payFailReason;

    @OneToOne(cascade = CascadeType.ALL)
    private Orders order;

    @ManyToOne(optional = false)
    private Profile profile;

    private Payment(PayType payType, BigDecimal amount, Orders order, String productName, Profile profile) {
        this.payType = payType;
        this.amount = amount;
        this.order = order;
        this.productName = productName;
        this.profile = profile;
    }

    public static Payment of(PayType payType, BigDecimal amount, Orders order, String productName, Profile profile) {
        return new Payment(payType, amount, order, productName, profile);
    }
}
