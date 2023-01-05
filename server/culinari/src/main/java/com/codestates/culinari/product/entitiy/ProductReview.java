package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 65554)
    private String content;

    @ManyToOne(optional = false)
    private Profile profile;

    @ManyToOne
    private Product product;

}
