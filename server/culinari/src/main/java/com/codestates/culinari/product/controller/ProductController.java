package com.codestates.culinari.product.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import com.codestates.culinari.product.dto.response.ProductInquiryResponse;
import com.codestates.culinari.product.dto.response.ProductResponse;
import com.codestates.culinari.product.dto.response.ProductReviewResponse;
import com.codestates.culinari.product.service.ProductCsService;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.service.ProfileService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductCsService productCsService;
    private final PaginationService paginationService;
    private final ProfileService profileService;

    //상품 상세 조회
    @GetMapping("/{product-id}")
    public ResponseEntity getProduct(
            @PathVariable("product-id") Long productId){

        ProductResponse product = productService.readProductWithCS(productId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(product), HttpStatus.OK);
    }
    @PostMapping("/{product-id}/like")
    public ResponseEntity postProductLike(
            @PathVariable("product-id") Long productId,
            @AuthenticationPrincipal CustomPrincipal principal){

        productService.createProductLike(productId,principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}/like")
    public ResponseEntity deleteProductLike(
            @PathVariable("product-id") Long productId,
            @AuthenticationPrincipal CustomPrincipal principal){

        productService.deleteProductLike(productId,principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{product-id}/inquiry")
    public ResponseEntity getProductInquiry(
            @PathVariable("product-id") Long productId,
            @Min (0) @RequestParam(defaultValue = "0",required = false) int page,
            @Positive @RequestParam(defaultValue = "10", required = false) int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductInquiryResponse> productInquiryPage = productCsService.readProductInquiry(productId, pageable).map(ProductInquiryResponse::from);
        List<ProductInquiryResponse> productInquiry = productInquiryPage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, productInquiryPage.getTotalPages());

        return new ResponseEntity(
                new PageResponseDto<>(productInquiry, productInquiryPage, barNumber), HttpStatus.OK);
    }

    @GetMapping("/{product-id}/review")
    public ResponseEntity getProductReview(
            @PathVariable("product-id") Long productId,
            @RequestParam(value = "sorted_type",required = false) String sortedType,
            @Min (0) @RequestParam(defaultValue = "0",required = false) int page,
            @Positive @RequestParam(defaultValue = "5", required = false) int size
    ){
        if(sortedType == null) sortedType = "newest";
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductReviewResponse> productReviewPage = productCsService.readProductReviewWithSortedType(sortedType,productId, pageable).map(ProductReviewResponse::from);
        List<ProductReviewResponse> productReview = productReviewPage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, productReviewPage.getTotalPages());

        return new ResponseEntity(
                new PageResponseDto<>(productReview, productReviewPage, barNumber), HttpStatus.OK);
    }

    //상품 문의 등록
    @PostMapping("/{product-id}/inquiry")
    public ResponseEntity postProductInquiry(
            @PathVariable("product-id") Long productId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductInquiryRequest productInquiryRequest){

         productCsService.createProductInquiry(productInquiryRequest, principal, productId);

        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
    //상품 후기 등록
    @PostMapping(value = "/{product-id}/review", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postProductReview(
            @PathVariable("product-id") Long productId,
            @RequestParam("order-id") Long orderId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestPart(value = "request") ProductReviewRequest productReviewRequest,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {


        productCsService.createProductReview(productReviewRequest,principal,productId,orderId,images);


        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }


    //상품 문의 수정
    @PatchMapping("/inquiry/{inquiry-id}")
    public ResponseEntity patchProductInquiry(
            @PathVariable("inquiry-id") Long productInquiryId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductInquiryRequest productInquiryRequest){

        productCsService.updateProductInquiry(productInquiryRequest,principal,productInquiryId);

        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
    //상품 리뷰 수정
    @PatchMapping("/review/{review-id}")
    public ResponseEntity patchProductReview(
            @PathVariable("review-id") Long productReviewId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductReviewRequest productReviewRequest){

       productCsService.updateProductReview(productReviewRequest,principal,productReviewId);

        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
    //상품 리뷰 좋아요
    @PatchMapping("/review/{review-id}/like")
    public ResponseEntity patchProductReviewLike(
            @PathVariable("review-id") Long productReviewId,
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestBody ProductReviewLikeRequest productReviewLikeRequest){

        productCsService.updateLike(productReviewLikeRequest, principal,productReviewId);

        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }

    //문의 삭제
    @DeleteMapping("/inquiry/{inquiry-id}")
    public ResponseEntity deleteProductInquiry(
            @PathVariable("inquiry-id") Long productInquiryId,
            @AuthenticationPrincipal CustomPrincipal principal){

        productCsService.deleteProductInquiry(principal,productInquiryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //리뷰 삭제
    @DeleteMapping("/review/{review-id}")
    public ResponseEntity deleteProductReview(
            @PathVariable("review-id") Long productReviewId,
            @RequestParam("product-id") Long productId,
            @AuthenticationPrincipal CustomPrincipal principal){

        productCsService.deleteProductReview(principal,productId,productReviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
