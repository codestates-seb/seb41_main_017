package com.codestates.culinari.product.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.service.OrdersService;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.dto.ProductLikeDto;
import com.codestates.culinari.product.service.ProductCsService;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.dto.response.ProfileMyPageInquiryResponse;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewEnableResponse;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewExistResponse;
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
import java.util.Objects;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@RestController
public class MypageController {
    private final ProductService productService;
    private final ProductCsService productCsService;
    private final OrdersService ordersService;
    private final PaginationService paginationService;

    @GetMapping("/review")
    public ResponseEntity getReviewList(
            @AuthenticationPrincipal CustomPrincipal principal,
            @RequestParam("type") String type,
            @Min(0) @RequestParam(defaultValue ="0",required = false) int page,
            @RequestParam(defaultValue = "4", required = false) int size
    ){

        Pageable pageable = PageRequest.of(page, size);

        if(Objects.equals(type, "nonexistent")){
            Page<ProfileMyPageReviewEnableResponse> response = ordersService.readEnableReviewOrder(principal,pageable);
            List<ProfileMyPageReviewEnableResponse> responseList = response.getContent();
            List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, response.getTotalPages());
            return new ResponseEntity<>(
                    new PageResponseDto<>(responseList,response,barNumber), HttpStatus.OK);
        }else if(Objects.equals(type, "exist"))
        {
            Page<ProfileMyPageReviewExistResponse> response = productCsService.readProductReview(principal, pageable);
            List<ProfileMyPageReviewExistResponse> responseList = response.getContent();
            List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, response.getTotalPages());
            return new ResponseEntity<>(
                    new PageResponseDto<>(responseList, response, barNumber), HttpStatus.OK);
        }
        return null;
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
