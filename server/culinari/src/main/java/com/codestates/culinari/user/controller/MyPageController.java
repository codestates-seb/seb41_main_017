package com.codestates.culinari.user.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.dto.response.MyPageCountResponse;
import com.codestates.culinari.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/count")
    public ResponseEntity getCount(@AuthenticationPrincipal CustomPrincipal customPrincipal){

        MyPageCountResponse response = myPageService.getMyPageCountData(customPrincipal);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.OK);
    }
}
