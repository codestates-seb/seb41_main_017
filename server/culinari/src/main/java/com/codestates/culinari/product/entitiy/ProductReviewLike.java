package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.user.entitiy.Profile;
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
    @OneToOne
    @JoinColumn(name = "product_review_id")
    private ProductReview productReview;

    @ElementCollection
    private final List<Long> productReviewProfileIds = new ArrayList<>();

    public ProductReviewLike(Long likeNum) {
        this.likeNum = likeNum;
    }

    public static ProductReviewLike of(Long likeNum){
        return new ProductReviewLike(likeNum);
    }
    public void productReviewProfileIds(Long userId){
        this.productReviewProfileIds.add(userId);
    }

}
