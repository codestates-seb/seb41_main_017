package com.codestates.culinari.customercenter.dto;

import com.codestates.culinari.customercenter.entity.CsFrequentlyAskedQuestion;

public record CsFrequentlyAskedQuestionDto(
        Long id,
        String title,
        String content,
        String category
) {
    public static CsFrequentlyAskedQuestionDto of(String title, String content, String category) {
        return new CsFrequentlyAskedQuestionDto(
                null,
                title,
                content,
                category
        );
    }

    public static CsFrequentlyAskedQuestionDto from(CsFrequentlyAskedQuestion csFrequentlyAskedQuestion) {
        return new CsFrequentlyAskedQuestionDto(
                csFrequentlyAskedQuestion.getId(),
                csFrequentlyAskedQuestion.getTitle(),
                csFrequentlyAskedQuestion.getContent(),
                csFrequentlyAskedQuestion.getCategory()
        );
    }

    public CsFrequentlyAskedQuestion toEntity() {
        return CsFrequentlyAskedQuestion.of(
                title,
                content,
                category
        );
    }
}
