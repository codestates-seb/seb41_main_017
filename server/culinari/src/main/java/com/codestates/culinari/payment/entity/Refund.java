package com.codestates.culinari.payment.entity;

import com.codestates.culinari.order.entitiy.Orders;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(nullable = false , length = 500)
    private String refundReason;

@ManyToOne
    private Orders orders;
}
