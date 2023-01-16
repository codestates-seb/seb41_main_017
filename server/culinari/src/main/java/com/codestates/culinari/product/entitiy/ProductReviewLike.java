package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductReviewLike extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Long likeNum = 0L;
    @Setter
    @OneToOne
    @JoinColumn(name = "product_review_id")
    private ProductReview productReview;

    @ElementCollection
    private final List<Long> productReviewProfileIds = new ArrayList<>();

    public ProductReviewLike(Long likeNum, ProductReview productReview){
        this.likeNum = likeNum;
        this.productReview = productReview;
    }
    public static ProductReviewLike of(Long likeNum, ProductReview productReview){
        return new ProductReviewLike(likeNum, productReview);
    }
    public void setProductReviewProfileIds(Long profileId){
        this.productReviewProfileIds.add(profileId);
    }

}
