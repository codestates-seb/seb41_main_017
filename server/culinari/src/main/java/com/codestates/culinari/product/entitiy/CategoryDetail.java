package com.codestates.culinari.product.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false , unique = true, name ="categoryDetailCode", length = 20)
    String categoryDetailCode;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name ="categoryCode", referencedColumnName = "categoryCode")
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "categoryDetail")
    private List<Product> products = new ArrayList<>();

    public CategoryDetail(String name, String categoryDetailCode) {
        this.name = name;
        this.categoryDetailCode = categoryDetailCode;
    }

    public static CategoryDetail of(String name, String categoryDetailCode){
        return new CategoryDetail(name, categoryDetailCode);
    }
}
