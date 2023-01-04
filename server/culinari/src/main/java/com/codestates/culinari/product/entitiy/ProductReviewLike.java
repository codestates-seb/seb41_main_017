package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ProductReviewLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 논의
    @OneToOne
    private ProductReview productReview;

    //논의
    @ManyToOne
    private Profile profile;
}
