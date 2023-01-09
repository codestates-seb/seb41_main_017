package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategoryDetailCategoryDetailCodeContaining(String categoryCode ,Pageable pageable);
    Page<Product> findAllByCategoryDetailCategoryDetailCode(String categoryCode, Pageable pageable);
}
