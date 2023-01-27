package com.codestates.culinari.product.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class ProductReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Setter
    private String imgUrl;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_review_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductReview productReview;


    public ProductReviewImage( String imgUrl, ProductReview productReview){
        this.imgUrl = imgUrl;
        this.productReview = productReview;
    }

    public static ProductReviewImage of( String imgUrl, ProductReview productReview) {
        return new ProductReviewImage( imgUrl, productReview);
    }
}
