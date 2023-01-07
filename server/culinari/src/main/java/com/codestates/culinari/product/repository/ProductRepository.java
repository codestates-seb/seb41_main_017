package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}