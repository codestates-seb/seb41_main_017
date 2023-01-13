package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.response.ProductResponseToPage;
import com.codestates.culinari.product.service.ProductService;
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
    private final PaginationService paginationService;

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
