package com.codestates.culinari.product.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import com.codestates.culinari.product.dto.response.ProductInquiryResponse;
import com.codestates.culinari.product.dto.response.ProductResponseWithCustomerService;
import com.codestates.culinari.product.dto.response.ProductReviewResponse;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.product.service.ProductCsService;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductCsService productCsService;
    private final ProfileService profileService;

    //상품 상세 조회
    @GetMapping("/{product-id}")
    public ResponseEntity getProduct(
            @PathVariable("product-id") Long productId){


        ProductResponseWithCustomerService product = ProductResponseWithCustomerService.from(productService.readProduct(productId));
        return new ResponseEntity<>(
                new SingleResponseDto<>(product), HttpStatus.OK);
    }
    //상품 문의 등록
    @PostMapping("/{product-id}/inquiry")
    public ResponseEntity postProductInquiry(
            @PathVariable("product-id") Long productId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductInquiryRequest productInquiryRequest){


        ProductInquiryDto productInquiry = productCsService.createProductInquiry(productInquiryRequest, principal, productId);

        return new ResponseEntity(
                new SingleResponseDto<>(productInquiry),HttpStatus.CREATED);
    }
    //상품 후기 등록
    @PostMapping("/{product-id}/review")
    public ResponseEntity postProductReview(
            @PathVariable("product-id") Long productId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductReviewRequest productReviewRequest){

        ProductReviewResponse productReview = ProductReviewResponse.from(productCsService.createProductReview(productReviewRequest, principal, productId));

        return new ResponseEntity(
                new SingleResponseDto<>(productReview),HttpStatus.CREATED);
    }
    //상품 문의 수정
    @PatchMapping("/{product-id}/inquiry/{inquiry-id}")
    public ResponseEntity patchProductInquiry(
            @PathVariable("inquiry-id") Long productInquiryId,
            @RequestBody ProductInquiryRequest productInquiryRequest){

        ProductInquiryDto productInquiryDto = productCsService.updateProductInquiry(productInquiryRequest,productInquiryId);

        return new ResponseEntity(
                new SingleResponseDto<>(productInquiryDto),HttpStatus.CREATED);
    }
    //상품 리뷰 수정
    @PatchMapping("/{product-id}/review/{review-id}")
    public ResponseEntity patchProductReview(
            @PathVariable("review-id") Long productReviewId,
            @RequestBody ProductReviewRequest productReviewRequest){

        ProductReviewDto productReviewDto = productCsService.updateProductReview(productReviewRequest,productReviewId);

        return new ResponseEntity(
                new SingleResponseDto<>(productReviewDto),HttpStatus.CREATED);
    }
    //상품 리뷰 좋아요
    @PatchMapping("/{product-id}/review/{review-id}/like")
    public ResponseEntity patchProductReviewLike(
            @PathVariable("review-id") Long productReviewId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductReviewLikeRequest productReviewLikeRequest){

        ProductReviewDto productReviewDto = productCsService.updateLike(productReviewLikeRequest, principal,productReviewId);

        return new ResponseEntity(
                new SingleResponseDto<>(productReviewDto),HttpStatus.CREATED);
    }

    //문의 삭제
    @DeleteMapping("/{product-id}/inquiry/{inquiry-id}")
    public ResponseEntity deleteProductInquiry(
            @PathVariable("inquiry-id") Long productInquiryId){

        productCsService.deleteProductInquiry(productInquiryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //리뷰 삭제
    @DeleteMapping("/{product-id}/review/{review-id}")
    public ResponseEntity deleteProductReview(
            @PathVariable("review-id") Long productReviewId){

        productCsService.deleteProductReview(productReviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
