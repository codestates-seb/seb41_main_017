package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.dto.response.CategoryDetailListResponse;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryDetailService {

    @Transactional(readOnly = true)
    List<CategoryDetailListResponse> findAllCategory(String categoryCode);

    public Page<CategoryDetailDto> getCategoryDetail(String categoryCode, int page, int size);
}
