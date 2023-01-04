package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.OrderDetail;
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
public class Product {
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
    private String allergy_info;

    @Column(nullable = false)
    private Boolean refundable;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductInquiry> productInquiry = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReview = new ArrayList<>();

    @OneToOne
    private NutritionInfo nutritionInfo;

    // 굳이..?
    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
