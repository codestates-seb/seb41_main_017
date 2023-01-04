package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private Product product;

}
