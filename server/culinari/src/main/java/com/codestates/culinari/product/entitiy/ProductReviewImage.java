package com.codestates.culinari.product.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class ProductReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String originImageName;
    private String storeImageName;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_review_id")
    private ProductReview productReview;

    @Builder
    public ProductReviewImage( String originImageName, String storePath){
        this.originImageName = originImageName;
        this.storeImageName = storePath;
    }

    public static ProductReviewImage of(String originImageName, String storeImageName) {
        return new ProductReviewImage(originImageName, storeImageName);
    }
}
