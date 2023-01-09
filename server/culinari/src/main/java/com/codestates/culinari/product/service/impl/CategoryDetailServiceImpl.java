package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.repository.CategoryDetailRepository;
import com.codestates.culinari.product.service.CategoryDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryDetailServiceImpl implements CategoryDetailService {

    private final CategoryDetailRepository categoryDetailRepository;
    public CategoryDetailServiceImpl(CategoryDetailRepository categoryDetailRepository) {
        this.categoryDetailRepository = categoryDetailRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDetailDto> getCategoryDetail(String categoryDetailCode, int page, int size){
        return categoryDetailRepository.findByCategoryDetailCode(categoryDetailCode, PageRequest.of(page, size, Sort.by("id").descending()))
                .map(CategoryDetailDto::from);
    }
}
