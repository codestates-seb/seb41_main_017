package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.CategoryDto;
import com.codestates.culinari.product.dto.response.CategoryDetailListResponse;
import com.codestates.culinari.product.dto.response.CategoryListResponse;
import com.codestates.culinari.product.dto.response.CategoryWithDetailListResponse;
import com.codestates.culinari.product.dto.response.ProductResponseToPage;
import com.codestates.culinari.product.entitiy.Category;
import com.codestates.culinari.product.service.CategoryDetailService;
import com.codestates.culinari.product.service.CategoryService;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.response.SingleResponseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CategoryDetailService categoryDetailService;
    private final PaginationService paginationService;

    @GetMapping
    public ResponseEntity getCategoryList(){
        List<CategoryListResponse> categoryList = categoryService.findAllCategory();

        return new ResponseEntity<>(
                new SingleResponseDto<>(categoryList), HttpStatus.OK
        );
    }
    @GetMapping("categorydetail/{category-code}")
    public ResponseEntity getCategoryDetailList(
            @PathVariable("category-code") String categoryCode
    ){
        List<CategoryWithDetailListResponse> categoryDetailList = categoryService.getCategoryWithDetailList(categoryCode);

        return new ResponseEntity<>(
                new SingleResponseDto<>(categoryDetailList), HttpStatus.OK
        );
    }

    @GetMapping("/{category-code}")
    public ResponseEntity getCategory(
            @PathVariable("category-code") String categoryCode,
            @RequestParam(required = false, value = "sorted_type") String sortedType,
            @Min (0) @RequestParam(defaultValue = "0",required = false) int page,
            @Positive @RequestParam(defaultValue = "15", required = false) int size){

        Pageable pageable = PageRequest.of(page, size);
        if(sortedType == null) sortedType = "newest";

            Page<ProductResponseToPage> categoryPage = productService.readProductWithCategoryCode(categoryCode,sortedType,pageable).map(ProductResponseToPage::from);
            List<ProductResponseToPage> category = categoryPage.getContent();
            List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, categoryPage.getTotalPages());

            return new ResponseEntity<>(
                    new PageResponseDto<>(category,categoryPage,barNumber),HttpStatus.OK);
    }
}
