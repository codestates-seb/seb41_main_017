package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface CategoryService {
    public Page<CategoryDto> getCategory(String categoryCode, int page, int size);
}
