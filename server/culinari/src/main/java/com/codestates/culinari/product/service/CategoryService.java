package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public Page<CategoryDto> getCategory(String categoryCode, Pageable pageable);
}
