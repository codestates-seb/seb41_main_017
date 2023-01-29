package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.CategoryDto;
import com.codestates.culinari.product.dto.response.CategoryListResponse;
import com.codestates.culinari.product.dto.response.CategoryWithDetailListResponse;
import com.codestates.culinari.product.repository.CategoryRepository;
import com.codestates.culinari.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    //카테고리 리스트
    @Transactional(readOnly = true)
    @Override
    public List<CategoryListResponse> findAllCategory(){
        return categoryRepository.findAll().stream().map(CategoryListResponse::from).collect(Collectors.toList());
    }

    @Override
    public List<CategoryWithDetailListResponse> getCategoryWithDetailList(String categoryCode){
        return categoryRepository.findByCategoryCode(categoryCode).stream().map(CategoryWithDetailListResponse::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> getCategory(String categoryCode, int page, int size){
        return categoryRepository.findByCategoryCode(categoryCode, PageRequest.of(page, size, Sort.by("id").descending()))
                .map(CategoryDto::from);
    }
}
