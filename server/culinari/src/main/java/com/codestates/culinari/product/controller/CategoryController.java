package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.response.CategoryDetailResponseDto;
import com.codestates.culinari.product.dto.response.CategoryResponseDto;
import com.codestates.culinari.product.service.CategoryDetailService;
import com.codestates.culinari.product.service.CategoryService;
import jakarta.validation.constraints.Positive;
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
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDetailService categoryDetailService;
    private final PaginationService paginationService;

    @GetMapping("/{category-code}")
    public ResponseEntity getCategory(
            @PathVariable("category-code") String categoryCode,
            @Positive Pageable pageable){
        if(categoryCode.length() <=3 ) {

            Page<CategoryResponseDto> categoryPage = categoryService.getCategory(categoryCode, pageable).map(CategoryResponseDto::from);
            List<CategoryResponseDto> category = categoryPage.stream().toList();
            List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), categoryPage.getTotalPages());
            return new ResponseEntity<>(
                    new PageResponseDto<>(category,pageable,barNumber),HttpStatus.OK);
        } else {

            Page<CategoryDetailResponseDto> categoryDetailPage = categoryDetailService.getCategoryDetail(categoryCode, pageable).map(CategoryDetailResponseDto::from);
            List<CategoryDetailResponseDto> category = categoryDetailPage.stream().toList();
            List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), categoryDetailPage.getTotalPages());

            return new ResponseEntity<>(
                    new PageResponseDto<>(category,pageable,barNumber),HttpStatus.OK);
        }
    }
}
