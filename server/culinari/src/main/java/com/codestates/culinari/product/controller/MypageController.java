package com.codestates.culinari.product.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.ProductLikeDto;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.dto.response.ProfileMyPageInquiryResponse;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewResponse;
import com.codestates.culinari.user.service.ProfileService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@RestController
public class MypageController {

    private final ProfileService profileService;
    private final ProductService productService;
    private final PaginationService paginationService;

    @GetMapping("/inquiry")
    public ResponseEntity getInquiryList(
            @AuthenticationPrincipal CustomPrincipal principal){

        ProfileMyPageInquiryResponse response = ProfileMyPageInquiryResponse.from(profileService.readMyPageReview(principal));
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity getReviewList(
            @AuthenticationPrincipal CustomPrincipal principal){

        ProfileMyPageReviewResponse response = ProfileMyPageReviewResponse.from(profileService.readMyPageReview(principal));
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/productlike")
    public ResponseEntity getProductLike(
            @AuthenticationPrincipal CustomPrincipal principal,
            @Min(0) @RequestParam(defaultValue = "0" ,required = false) int page,
            @RequestParam(defaultValue = "12", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductLikeDto> productLikeResponsePage = productService.readProductLike(principal, pageable);
        List<ProductLikeDto> productLikeDtoList = productLikeResponsePage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, productLikeResponsePage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(productLikeDtoList, productLikeResponsePage, barNumber), HttpStatus.OK
        );
    }
}
