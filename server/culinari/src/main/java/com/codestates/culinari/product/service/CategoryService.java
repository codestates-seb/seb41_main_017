package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.CategoryDto;
import com.codestates.culinari.product.dto.response.CategoryListResponse;
import com.codestates.culinari.product.dto.response.CategoryWithDetailListResponse;
import com.codestates.culinari.product.entitiy.Category;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    @Transactional(readOnly = true)
    List<CategoryListResponse> findAllCategory();

//    @Transactional(readOnly = true)
//    List<CategoryDto> getCategoryList();

    List<CategoryWithDetailListResponse> getCategoryWithDetailList(String categoryCode);

    public Page<CategoryDto> getCategory(String categoryCode, int page, int size);
}
