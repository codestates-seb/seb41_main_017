package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDetailService {
    public Page<CategoryDetailDto> getCategoryDetail(String categoryCode, int page, int size);

}
