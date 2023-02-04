package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    Page<ProductReview> findByProfileId(Long profileId, Pageable pageable);

    Page<ProductReview> findByProductId(Long productId, Pageable pageable);
    ProductReview deleteByProductIdAndOrderDetailIdAndProfileId(Long productId, Long orderDetailId, Long profileId);
}
