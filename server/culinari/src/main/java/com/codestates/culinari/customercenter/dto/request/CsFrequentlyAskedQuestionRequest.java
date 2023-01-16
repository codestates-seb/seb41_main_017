package com.codestates.culinari.customercenter.dto.request;

import com.codestates.culinari.customercenter.dto.CsFrequentlyAskedQuestionDto;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CsFrequentlyAskedQuestionRequest(
        @NotBlank(message = "제목 입력은 필수입니다.")
        @Length(max = 255, message = "제목 길이는 255자 를 넘으면 안됩니다.")
        String title,

        @NotBlank(message = "내용 입력은 필수입니다.")
        @Length(max = 65554, message = "내용 길이는 65554 를 넘으면 안됩니다.")
        String content,

        @NotBlank(message = "제목 입력은 필수입니다.")
        @Length(max = 20, message = "카테고리 길이는 20자 를 넘으면 안됩니다.")
        String category
) {
    public static CsFrequentlyAskedQuestionRequest of(String title, String content, String category) {
        return new CsFrequentlyAskedQuestionRequest(
                title,
                content,
                category
        );
    }

    public CsFrequentlyAskedQuestionDto toDto(){
        return CsFrequentlyAskedQuestionDto.of(
                title,
                content,
                category
        );
    }
}
