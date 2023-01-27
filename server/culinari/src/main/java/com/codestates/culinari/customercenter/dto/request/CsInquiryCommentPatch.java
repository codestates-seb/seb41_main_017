package com.codestates.culinari.customercenter.dto.request;

import org.hibernate.validator.constraints.Length;

public record CsInquiryCommentPatch(

        @Length(max = 255, message = "제목 길이는 255자 를 넘으면 안됩니다.")
        String title,

        @Length(max = 10000, message = "내용 길이는 10000자 를 넘으면 안됩니다.")
        String content
) {
}
