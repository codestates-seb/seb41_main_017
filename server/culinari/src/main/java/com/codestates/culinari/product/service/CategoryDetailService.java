package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDetailService {
    public Page<CategoryDetailDto> getCategoryDetail(String categoryCode, Pageable pageable);

}
