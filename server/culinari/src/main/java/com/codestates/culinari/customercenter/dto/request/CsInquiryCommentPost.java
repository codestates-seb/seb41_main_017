package com.codestates.culinari.customercenter.dto.request;

import com.codestates.culinari.customercenter.dto.CsInquiryCommentDto;
import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CsInquiryCommentPost(

        @NotBlank(message = "제목 입력은 필수입니다.")
        @Length(max = 255, message = "제목 길이는 255자 를 넘으면 안됩니다.")
        String title,

        @NotBlank(message = "내용 입력은 필수입니다.")
        @Length(max = 10000, message = "내용 길이는 10000자 를 넘으면 안됩니다.")
        String content
) {

    public CsInquiryCommentPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public CsInquiryCommentDto toDto(CsInquiry csInquiry, Profile profile){
        return CsInquiryCommentDto.of(
                title,
                content,
                csInquiry,
                profile
        );
    }
}
