package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductReviewLike extends AuditingFields {
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
// 멘토님께 식별관계를 통해 복합키 생성을 하여 중복방지가 가능한 지?

