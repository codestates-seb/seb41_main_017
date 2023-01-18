package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.customercenter.dto.request.CsFrequentlyAskedQuestionRequest;
import com.codestates.culinari.customercenter.dto.response.CsFrequentlyAskedQuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerFrequentlyAskedQuestionService {
    Page<CsFrequentlyAskedQuestionResponse> readFrequentlyAskedQuestionPage(String category,Pageable pageable);

    void createCsFrequentlyAskedQuestion(CsFrequentlyAskedQuestionRequest csFrequentlyAskedQuestionRequest);
}
