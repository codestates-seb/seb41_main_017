package com.codestates.culinari.order.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.product.entitiy.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderDetail extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusType statusType;

    @ManyToOne(optional = false)
    private Orders orders;

    @ManyToOne(optional = false)
    private Product product;
}
