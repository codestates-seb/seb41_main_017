package com.codestates.culinari.order.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cart extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    private Profile profile;

    @ManyToOne(optional = false)
    private Product product;

}

// 장바구니 삭제 하는게 관리가 용이한 지?
