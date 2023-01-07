package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String shipping;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private String packaging;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private String countryOfOrigin;

    @Column(nullable = false , length = 300)
    private String allergyInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name="categoryDetailCode", referencedColumnName = "categoryDetailCode")
    private CategoryDetail categoryDetail;

    @OneToMany(mappedBy = "product")
    private List<ProductInquiry> productInquiry = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReview = new ArrayList<>();

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

}
