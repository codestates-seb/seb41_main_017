package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.customercenter.dto.request.CsFrequentlyAskedQuestionRequest;
import com.codestates.culinari.customercenter.dto.response.CsFrequentlyAskedQuestionResponse;
import com.codestates.culinari.customercenter.service.CustomerFrequentlyAskedQuestionService;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
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
@RequestMapping("/board/faq")
public class CsFrequentlyAskedQuestionController {

    private final CustomerFrequentlyAskedQuestionService csFaqService;
    private final PaginationService paginationService;

    @GetMapping
    public ResponseEntity getNoticePage(@RequestParam(required = false) String category,
            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CsFrequentlyAskedQuestionResponse> CsFrequentlyAskedQuestionResponsePage = csFaqService.readFrequentlyAskedQuestionPage(category, pageable);
        List<CsFrequentlyAskedQuestionResponse> CsFrequentlyAskedQuestionResponses = CsFrequentlyAskedQuestionResponsePage.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), CsFrequentlyAskedQuestionResponsePage.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(CsFrequentlyAskedQuestionResponses, CsFrequentlyAskedQuestionResponsePage, barNumber),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity postNewNotice(@RequestBody CsFrequentlyAskedQuestionRequest csFrequentlyAskedQuestionRequest) {
        csFaqService.createCsFrequentlyAskedQuestion(csFrequentlyAskedQuestionRequest);

        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

}
