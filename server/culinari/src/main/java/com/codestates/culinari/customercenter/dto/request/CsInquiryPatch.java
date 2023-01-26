package com.codestates.culinari.customercenter.dto.request;

import org.hibernate.validator.constraints.Length;

public record CsInquiryPatch(

        @Length(max = 255, message = "제목 길이는 255자 를 넘으면 안됩니다.")
        String title,

        @Length(max = 65564, message = "내용 길이는 65564자 를 넘으면 안됩니다.")
        String content,

        @Length(max = 20, message = "카테고리 길이는 20자 를 넘으면 안됩니다.")
        String category
) {

}
