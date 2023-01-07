package com.codestates.culinari.product.repository;

import com.codestates.culinari.product.entitiy.CategoryDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {

    Page<CategoryDetail> findByCategoryDetailCode(String categoryDetailCode, Pageable pageable);
}