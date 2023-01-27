package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.dto.response.CategoryDetailListResponse;
import com.codestates.culinari.product.repository.CategoryDetailRepository;
import com.codestates.culinari.product.service.CategoryDetailService;
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
public class CategoryDetailServiceImpl implements CategoryDetailService {

    private final CategoryDetailRepository categoryDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDetailListResponse> findAllCategory(String categoryCode){
        return categoryDetailRepository.findByCategory_CategoryCode(categoryCode).stream().map(CategoryDetailListResponse::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CategoryDetailDto> getCategoryDetail(String categoryDetailCode, int page, int size){
        return categoryDetailRepository.findByCategoryDetailCode(categoryDetailCode, PageRequest.of(page, size, Sort.by("id").descending()))
                .map(CategoryDetailDto::from);
    }
}
