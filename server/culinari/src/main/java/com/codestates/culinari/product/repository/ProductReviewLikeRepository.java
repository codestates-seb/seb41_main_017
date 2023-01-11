package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.ProductReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewLikeRepository extends JpaRepository<ProductReviewLike, Long> {
}