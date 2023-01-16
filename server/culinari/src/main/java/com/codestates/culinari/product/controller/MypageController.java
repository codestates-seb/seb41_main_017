package com.codestates.culinari.product.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.dto.response.ProfileMyPageInquiryResponse;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewResponse;
import com.codestates.culinari.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@RestController
public class MypageController {

    private final ProfileService profileService;

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

}
