package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.response.CategoryDetailResponseDto;
import com.codestates.culinari.product.dto.response.CategoryResponseDto;
import com.codestates.culinari.product.dto.response.ProductResponseToPageDto;
import com.codestates.culinari.product.service.CategoryDetailService;
import com.codestates.culinari.product.service.CategoryService;
import com.codestates.culinari.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDetailService categoryDetailService;
    private final ProductService productService;
    private final PaginationService paginationService;

    @GetMapping("/{category-code}")
    public ResponseEntity getCategory(
            @PathVariable("category-code") String categoryCode,
            @Validated @Positive @RequestParam(required = false) int page,
            @Validated @Positive @RequestParam(required = false) int size){

            Page<ProductResponseToPageDto> categoryPage = productService.readProductWithCategoryCode(categoryCode, page-1,size).map(ProductResponseToPageDto::from);
            List<ProductResponseToPageDto> category = categoryPage.getContent();
            List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, categoryPage.getTotalPages());
            return new ResponseEntity<>(
                    new PageResponseDto<>(category,categoryPage,barNumber),HttpStatus.OK);
    }
}
