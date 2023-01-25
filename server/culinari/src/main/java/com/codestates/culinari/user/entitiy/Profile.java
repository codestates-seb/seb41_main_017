package com.codestates.culinari.user.entitiy;

import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.dto.request.ProfilePatchRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal point;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "profile")
    private final List<ProductInquiry> productInquiry = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "profile")
    private final List<ProductReview> productReview = new ArrayList<>();

    public Profile(String name, String email, String phoneNumber, BigDecimal point, GenderType gender, LocalDate birthDate) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public static Profile of(String name, String email, String phoneNumber, BigDecimal point, GenderType gender, LocalDate birthDate) {
        return new Profile(name, email, phoneNumber, point, gender, birthDate);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updateGender(GenderType gender) {
        this.gender = gender;
    }

    public void updateBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
