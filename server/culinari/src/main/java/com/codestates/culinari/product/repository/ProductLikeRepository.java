package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.ProductLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    Page<ProductLike> findAllByProfileId(Long profileId, Pageable pageable);

    ProductLike findByProductIdAndProfileId(Long productId, Long profileId);

    void deleteByProductId(Long productId);

    Long countByProfile_Id(Long profileId);
}
