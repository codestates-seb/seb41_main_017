package com.codestates.culinari.user.entitiy;

import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.product.entitiy.ProductReview;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String name;

    @Column(nullable = false, length = 10)
    private String email;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private BigDecimal point;

    @Column(nullable = false, length = 100)
    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "profile")
    private List<ProductReview> productReviews = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<ProductInquiry> productInquiries = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<Users> users = new ArrayList<>();

    //주문 내역이나 결제 내역 표시..?
    @OneToMany(mappedBy = "profile")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<Orders> orders = new ArrayList<>();
}
