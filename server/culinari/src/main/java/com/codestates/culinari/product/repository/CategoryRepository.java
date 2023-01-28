package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByCategoryCode(String categoryCode, Pageable pageable);

    List<Category> findByCategoryCode(String categoryCode);
}
