package com.codestates.culinari.customercenter.service.impl;

import com.codestates.culinari.customercenter.dto.CsFrequentlyAskedQuestionDto;
import com.codestates.culinari.customercenter.dto.request.CsFrequentlyAskedQuestionRequest;
import com.codestates.culinari.customercenter.dto.response.CsFrequentlyAskedQuestionResponse;
import com.codestates.culinari.customercenter.repository.CsFrequentlyAskedQuestionRepository;
import com.codestates.culinari.customercenter.service.CustomerFrequentlyAskedQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerFrequentlyAskedQuestionImpl implements CustomerFrequentlyAskedQuestionService {

    private final CsFrequentlyAskedQuestionRepository csFAQRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CsFrequentlyAskedQuestionResponse> readFrequentlyAskedQuestionPage(String category,Pageable pageable) {
        if (Objects.isNull(category) || category.isBlank()) {
            return csFAQRepository.findAll(pageable)
                    .map(CsFrequentlyAskedQuestionDto::from)
                    .map(CsFrequentlyAskedQuestionResponse::from);
        }

        return csFAQRepository.findCategoryFilter(category, pageable)
                .map(CsFrequentlyAskedQuestionDto::from)
                .map(CsFrequentlyAskedQuestionResponse::from);
    }

    @Override
    public void createCsFrequentlyAskedQuestion(CsFrequentlyAskedQuestionRequest csFrequentlyAskedQuestionRequest) {
        CsFrequentlyAskedQuestionDto csFrequentlyAskedQuestionDto = csFrequentlyAskedQuestionRequest.toDto();

        csFAQRepository.save(csFrequentlyAskedQuestionDto.toEntity());
    }
}
