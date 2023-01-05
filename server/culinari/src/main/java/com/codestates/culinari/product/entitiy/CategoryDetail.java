package com.codestates.culinari.product.entitiy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CategoryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false , unique = true)
    String categoryDetailCode;

    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "categoryDetail")
    private List<Product> products = new ArrayList<>();
}
