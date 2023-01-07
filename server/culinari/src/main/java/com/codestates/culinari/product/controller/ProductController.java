package com.codestates.culinari.product.controller;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.response.ProductResponseWithCSDto;
import com.codestates.culinari.product.service.ProductCsService;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductCsService productCsService;


    @GetMapping("/{product-id}")
    public ResponseEntity getProduct(
            @PathVariable("product-id") Long productId){

        ProductResponseWithCSDto product = ProductResponseWithCSDto.from(productService.readProduct(productId));
        return new ResponseEntity<>(
                new SingleResponseDto<>(product), HttpStatus.OK);
    }

    @PostMapping("/{product-id}/inquiry")
    public ResponseEntity postProductInquiry(
            @PathVariable("product-id") Long productId,
            @RequestBody ProductInquiryRequest productInquiryRequest){

         ProductInquiryDto productInquiryDto = productCsService.createProductInquiry(productInquiryRequest,productId);


        return new ResponseEntity(
                new SingleResponseDto<>(productInquiryDto),HttpStatus.CREATED);
    }
}
