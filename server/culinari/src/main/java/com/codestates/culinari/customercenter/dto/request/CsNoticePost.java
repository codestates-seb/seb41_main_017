package com.codestates.culinari.customercenter.dto.request;

import com.codestates.culinari.customercenter.dto.CsNoticeDto;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CsNoticePost(
        @NotBlank(message = "제목 입력은 필수입니다.")
        @Length(max = 255, message = "제목 길이는 255자 를 넘으면 안됩니다.")
        String title,

        @NotBlank(message = "내용 입력은 필수입니다.")
        String content
) {

    public static CsNoticePost of(String title, String content){
        return new CsNoticePost(
                title,
                content
        );
    }

    public CsNoticeDto toDto() {
        return CsNoticeDto.of(
                title,
                content
        );
    }
}
