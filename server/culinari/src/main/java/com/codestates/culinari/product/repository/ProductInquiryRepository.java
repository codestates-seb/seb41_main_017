package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.ProductInquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInquiryRepository extends JpaRepository<ProductInquiry, Long> {
    List<ProductInquiry> findByProfile_Id(Long profileId);

    Page<ProductInquiry> findByProductId(Long productId, Pageable pageable);
}
