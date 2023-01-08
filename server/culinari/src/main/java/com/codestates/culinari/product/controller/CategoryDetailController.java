package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.response.CategoryDetailResponseDto;
import com.codestates.culinari.product.service.CategoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryDetailController {

    private final CategoryDetailService categoryDetailService;
    private final PaginationService paginationService;

    @GetMapping("/{category-code}/{category-detail-code}")
    public ResponseEntity getCategory(
            @PathVariable("category-code") String categoryCode,
            @PathVariable("category-detail-code") String categoryDetailCode,
            Pageable pageable){

        Page<CategoryDetailResponseDto> categoryDetailPage = categoryDetailService.getCategoryDetail(categoryDetailCode, pageable).map(CategoryDetailResponseDto::from);
        List<CategoryDetailResponseDto> category = categoryDetailPage.stream().toList();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), categoryDetailPage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(category,pageable,barNumber),HttpStatus.OK);
    }
}
