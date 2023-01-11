package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.codestates.culinari.user.entitiy.Profile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @JsonBackReference
    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Profile profile;

    public ProductReview(String title, String content, Product product, Profile profile){
        this.title = title;
        this.content = content;
        this.product = product;
        this.profile = profile;
    }

    public static ProductReview of(String title, String content, Product product, Profile profile){
        return new ProductReview(title, content, product, profile);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
