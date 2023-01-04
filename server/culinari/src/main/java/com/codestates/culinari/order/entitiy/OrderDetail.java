package com.codestates.culinari.order.entitiy;

import com.codestates.culinari.product.entitiy.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusType statusType;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Product product;
}
