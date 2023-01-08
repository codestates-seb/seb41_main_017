package com.codestates.culinari.product.controller;

import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.response.ProductResponseToPageDto;
import com.codestates.culinari.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/collections")
@RestController
public class ProductCollectionsController {

    private final ProductService productService;
    private final PaginationService paginationService;

    @GetMapping("/newproduct")
    public ResponseEntity getNewestProducts(
            @RequestParam(required = false , value = "sorted_type") String sortedType,
            @RequestParam(required = false, value = "filter") String filter,
            Pageable pageable){

        if(sortedType == null) sortedType = "newest";

        Page<ProductResponseToPageDto> newestProductsPage = productService.readProductWithSortedType(sortedType, pageable).map(ProductResponseToPageDto::from);
        List<ProductResponseToPageDto> productPage = newestProductsPage.stream().toList();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), newestProductsPage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(productPage,pageable,barNumber), HttpStatus.OK);
    }
}
