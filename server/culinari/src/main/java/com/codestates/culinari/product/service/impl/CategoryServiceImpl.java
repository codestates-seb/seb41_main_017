package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.CategoryDto;
import com.codestates.culinari.product.repository.CategoryRepository;
import com.codestates.culinari.product.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> getCategory(String categoryCode, int page, int size){
        return categoryRepository.findByCategoryCode(categoryCode, PageRequest.of(page, size, Sort.by("id").descending()))
                .map(CategoryDto::from);
    }
}
