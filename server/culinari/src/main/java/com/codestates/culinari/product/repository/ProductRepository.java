package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByCategoryDetailCategoryDetailCodeContaining(String categoryCode ,Pageable pageable);

    Page<Product> findAllByCategoryDetailCategoryDetailCode(String categoryCode, Pageable pageable);
    Page<Product> findAllByNameContainingOrBrandContainingOrSellerContaining(String keyword1,String keyword2, String keyword3, Pageable pageable);
}
