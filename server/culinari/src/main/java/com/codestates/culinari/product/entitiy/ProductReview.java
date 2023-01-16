package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
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
    @Column(nullable = false, length = 255)
    private String title;
    @Setter
    @Column(nullable = false, length = 65554)
    private String content;

    @Enumerated(EnumType.STRING)
    @Setter
    @Column
    private ReviewStar reviewStar;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Product product;

    @JsonBackReference
    @ManyToOne(optional = false)
    private Profile profile;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL)
    @Setter
    private List<ProductReviewImage> productReviewImages = new ArrayList<>();

    @OneToOne(mappedBy = "productReview", cascade = CascadeType.ALL)
    private ProductReviewLike productReviewLike;

    public ProductReview(String title, String content, ReviewStar reviewStar, Product product, Profile profile ) {
        this.title = title;
        this.content = content;
        this.reviewStar = reviewStar;
        this.product = product;
        this.profile = profile;
    }

    public static ProductReview of(String title, String content, ReviewStar reviewStar, Product product, Profile profile){
        return new ProductReview(title, content, reviewStar, product, profile);
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
    public enum ReviewStar{
        ZERO("0"),
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5");
        @Getter
        private final String star;
        ReviewStar(String star){this.star = star;}
    }
}
