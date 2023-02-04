package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryCommentPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryCommentPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryCommentResponse;
import com.codestates.culinari.customercenter.service.CustomerInquiryCommentService;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board/inquiry")
public class CustomerInquiryCommentController {

    private final CustomerInquiryCommentService customerInquiryCommentService;
    private final PaginationService paginationService;

    @PostMapping("/{inquiry-id}/comments")
    public ResponseEntity postNewEnquireComment(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                                @PathVariable("inquiry-id") Long inquiryId,
                                                @RequestBody CsInquiryCommentPost csInquiryCommentPost) {

        CsInquiryCommentResponse csInquiryCommentResponse = customerInquiryCommentService.createEnquireComment(customPrincipal, csInquiryCommentPost, inquiryId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(csInquiryCommentResponse),
                HttpStatus.CREATED);
    }

    @GetMapping("/{inquiry-id}/comments")
    public ResponseEntity getEnquireCommentPage(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                                @PathVariable("inquiry-id") Long inquiryId,
                                                @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CsInquiryCommentResponse> csInquiryCommentResponsePage = customerInquiryCommentService.readEnquirieCommentPage(customPrincipal, pageable, inquiryId);
        List<CsInquiryCommentResponse> csInquiryCommentResponses = csInquiryCommentResponsePage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), csInquiryCommentResponsePage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(csInquiryCommentResponses, csInquiryCommentResponsePage, barNumber),
                HttpStatus.OK);
    }

    @GetMapping("/comments/{comment-id}")
    public ResponseEntity getEnquireComment(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                            @PathVariable("comment-id") Long commentId) {
        CsInquiryCommentResponse csInquiryCommentResponse = customerInquiryCommentService.readEnquireComment(customPrincipal, commentId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(csInquiryCommentResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity deleteEnquire(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                        @PathVariable("comment-id") Long commentId) {
        customerInquiryCommentService.deleteInquireComment(customPrincipal, commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/comments/{comment-id}")
    public ResponseEntity patchEnquire(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                       @PathVariable("comment-id") Long commentId,
                                       @RequestBody CsInquiryCommentPatch csInquiryCommentPatch) {
        customerInquiryCommentService.updateEnquireComment(customPrincipal, commentId, csInquiryCommentPatch);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
