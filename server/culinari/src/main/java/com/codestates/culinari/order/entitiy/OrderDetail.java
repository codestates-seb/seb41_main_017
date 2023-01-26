package com.codestates.culinari.order.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.order.constant.StatusType;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductReview;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @Setter
    @OneToOne
    private ProductReview productReview;

    private OrderDetail(Integer quantity, StatusType statusType, Orders orders, Product product) {
        this.quantity = quantity;
        this.statusType = statusType;
        this.orders = orders;
        this.product = product;
    }

    public static OrderDetail of(Integer quantity, StatusType statusType, Orders orders, Product product) {
        return new OrderDetail(quantity, statusType, orders, product);
    }

    public BigDecimal getPrice() { return product.getPrice().multiply(BigDecimal.valueOf(quantity)); }
}
