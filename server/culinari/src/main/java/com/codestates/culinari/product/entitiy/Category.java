package com.codestates.culinari.product.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, name = "categoryCode", length = 20)
    private String categoryCode;
@JsonManagedReference
    @OneToMany(mappedBy = "category")
    private final List<CategoryDetail> categoryDetails = new ArrayList<>();

    public Category(String name, String categoryCode) {
        this.name = name;
        this.categoryCode = categoryCode;
    }

    public static Category of(String name, String categoryCode){
        return new Category(name, categoryCode);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return id != null && id.equals(category.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
