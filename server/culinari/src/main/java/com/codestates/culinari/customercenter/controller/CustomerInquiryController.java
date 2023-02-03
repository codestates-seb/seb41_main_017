package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryResponse;
import com.codestates.culinari.customercenter.service.CustomerInquiryService;
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
public class CustomerInquiryController {

    private final CustomerInquiryService customerInquiryService;
    private final PaginationService paginationService;

    @PostMapping
    public ResponseEntity postNewEnquire(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                         @RequestBody CsInquiryPost csInquiryPost) {
        customerInquiryService.createEnquire(customPrincipal, csInquiryPost);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @GetMapping
    public ResponseEntity getEnquirePage(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                         @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CsInquiryResponse> csInquiryResponsePage = customerInquiryService.readInquiriePage(customPrincipal, pageable);
        List<CsInquiryResponse> csInquiryResponses = csInquiryResponsePage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), csInquiryResponsePage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(csInquiryResponses, csInquiryResponsePage, barNumber),
                HttpStatus.OK);
    }

    @GetMapping("/{inquiry-id}")
    public ResponseEntity getEnquire(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                     @PathVariable("inquiry-id") Long inquiryId) {
        CsInquiryResponse csInquiryResponse = customerInquiryService.readInquire(customPrincipal, inquiryId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(csInquiryResponse),
                HttpStatus.OK);
    }

    @DeleteMapping("/{inquiry-id}")
    public ResponseEntity deleteEnquire(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                        @PathVariable("inquiry-id") Long inquiryId) {
        customerInquiryService.deleteEnquire(customPrincipal, inquiryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{inquiry-id}")
    public ResponseEntity patchEnquire(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                       @PathVariable("inquiry-id") Long inquiryId,
                                       @RequestBody CsInquiryPatch csInquirypatch) {
        customerInquiryService.updateEnquire(customPrincipal, inquiryId, csInquirypatch);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
