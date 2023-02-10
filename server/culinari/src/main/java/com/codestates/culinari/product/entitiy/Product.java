package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 65554)
    private String content;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, length = 100)
    private String shipping;

    @Column(length = 50)
    private String brand;

    @Column(nullable = false, length = 50)
    private String seller;

    @Column(nullable = false, length = 100)
    private String packaging;

    @Column(length = 20)
    private String unit;

    @Column(length = 50)
    private String weight;

    @Column(nullable = false, length = 50)
    private String countryOfOrigin;

    @Column(length = 3000)
    private String allergyInfo;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="categoryDetailCode", referencedColumnName = "categoryDetailCode")
    private CategoryDetail categoryDetail;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private final List<ProductInquiry> productInquiry = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private final List<ProductReview> productReview = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private final List<ProductImage> productImages = new ArrayList<>();

    public Product(String name,
                   String content,
                   BigDecimal price,
                   String shipping,
                   String brand,
                   String seller,
                   String packaging,
                   String unit,
                   String weight,
                   String countryOfOrigin,
                   String allergyInfo,
                   CategoryDetail categoryDetail) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.shipping = shipping;
        this.brand = brand;
        this.seller = seller;
        this.packaging = packaging;
        this.unit = unit;
        this.weight = weight;
        this.countryOfOrigin = countryOfOrigin;
        this.allergyInfo = allergyInfo;
        this.categoryDetail = categoryDetail;
    }

    public static Product of (CategoryDetail categoryDetail,
                              String name,
                              String content,
                              BigDecimal price,
                              String shipping,
                              String brand,
                              String seller,
                              String packaging,
                              String unit,
                              String weight,
                              String countryOfOrigin,
                              String allergyInfo){
        return new Product(
                name,
                content,
                price,
                shipping,
                brand,
                seller,
                packaging,
                unit,
                weight,
                countryOfOrigin,
                allergyInfo,
                categoryDetail
        );
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
