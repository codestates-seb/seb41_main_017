package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.repository.querydsl.ProductRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
@Primary
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Page<Product> findAllByCategoryDetailCategoryDetailCodeContaining(String categoryCode ,Pageable pageable);

    Page<Product> findAllByCategoryDetailCategoryDetailCode(String categoryCode, Pageable pageable);
    Page<Product> findAllByNameContainingOrBrandContainingOrSellerContaining(String keyword1,String keyword2, String keyword3, Pageable pageable);
}
