package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}