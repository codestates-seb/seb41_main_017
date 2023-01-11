package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.ProductInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInquiryRepository extends JpaRepository<ProductInquiry, Long> {
}
