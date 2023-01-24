package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Profile profile;

    @ManyToOne(optional = false)
    private Product product;

    public ProductLike(Profile profile, Product product) {
        this.profile = profile;
        this.product = product;
    }

    public static ProductLike of(Profile profile, Product product){
        return new ProductLike(profile, product);
    }
}
