package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.customercenter.dto.request.CsNoticePost;
import com.codestates.culinari.customercenter.dto.response.CsNoticeResponse;
import com.codestates.culinari.customercenter.service.CustomerNoticeService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/board/notice")
public class CustomerNoticeController {

    private final CustomerNoticeService customerNoticeService;
    private final PaginationService paginationService;

    @PostMapping
    public ResponseEntity postNewNotice(@RequestBody CsNoticePost csNoticePost) {
        customerNoticeService.createNotice(csNoticePost);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @GetMapping
    public ResponseEntity getNoticePage(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CsNoticeResponse> csNoticeResponsePage = customerNoticeService.readNoticePage(pageable);
        List<CsNoticeResponse> csNoticeResponses = csNoticeResponsePage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), csNoticeResponsePage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(csNoticeResponses, csNoticeResponsePage, barNumber),
                HttpStatus.OK);
    }

    @GetMapping("/{notice-id}")
    public ResponseEntity getNotice(@PathVariable("notice-id") Long noticeId) {
        CsNoticeResponse csNoticeResponse = customerNoticeService.readNotice(noticeId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(csNoticeResponse),
                HttpStatus.OK);
    }
}
