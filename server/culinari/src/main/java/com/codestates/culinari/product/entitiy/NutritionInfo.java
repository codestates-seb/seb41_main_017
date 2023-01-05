package com.codestates.culinari.product.entitiy;

import com.codestates.culinari.audit.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class NutritionInfo extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer calories;

    @Column(nullable = false)
    private Integer carbohydrate;

    @Column(nullable = false)
    private Integer protein;

    @Column(nullable = false)
    private Integer fat;

    @Column(nullable = false)
    private Integer cholesterol;

    @Column(nullable = false)
    private Integer sodium;

    @Column(nullable = false)
    private Integer calcium;

    @Column(nullable = false)
    private Integer iron;

}
