package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.user.entitiy.Profile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductReview extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false, length = 65554)
    private String content;
    @Setter
    @Column
    private Integer reviewStar;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Product product;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Profile profile;

    @Setter
    @OneToOne
    private OrderDetail orderDetail;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL)
    private final List<ProductReviewImage> productReviewImages = new ArrayList<>();

    @OneToOne(mappedBy = "productReview", cascade = CascadeType.ALL)
    private ProductReviewLike productReviewLike;

    public ProductReview( String content, Integer reviewStar, Product product, Profile profile ) {
        this.content = content;
        this.reviewStar = reviewStar;
        this.product = product;
        this.profile = profile;
    }

    public static ProductReview of( String content, Integer reviewStar, Product product, Profile profile){
        return new ProductReview( content, reviewStar, product, profile);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setProductReviewLike(ProductReviewLike productReviewLike){
        this.productReviewLike = productReviewLike;
        if(productReviewLike.getProductReview() != this){
            productReviewLike.setProductReview(this);
        }
    }
}
