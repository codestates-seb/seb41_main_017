package com.codestates.culinari.customercenter.dto.response;

import com.codestates.culinari.customercenter.dto.CsFrequentlyAskedQuestionDto;

public record CsFrequentlyAskedQuestionResponse(
        Long id,
        String title,
        String content,
        String category
) {
    public static CsFrequentlyAskedQuestionResponse from(CsFrequentlyAskedQuestionDto csFrequentlyAskedQuestionDto) {
        return new CsFrequentlyAskedQuestionResponse(
                csFrequentlyAskedQuestionDto.id(),
                csFrequentlyAskedQuestionDto.title(),
                csFrequentlyAskedQuestionDto.content(),
                csFrequentlyAskedQuestionDto.category()
        );
    }
}
