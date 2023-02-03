package com.codestates.culinari.customercenter.dto.request;

import com.codestates.culinari.customercenter.dto.CsInquiryDto;
import com.codestates.culinari.user.entitiy.Profile;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CsInquiryPost(
        @NotBlank(message = "제목 입력은 필수입니다.")
        @Length(max = 255, message = "제목 길이는 255자 를 넘으면 안됩니다.")
        String title,

        @NotBlank(message = "내용 입력은 필수입니다.")
        String content,

        @NotBlank(message = "카테고리 입력은 필수입니다.")
        String category
) {

//    public static CsInquiryRequest of(String title, String content) {
//        return new CsInquiryRequest(
//                title,
//                content
//        );
//    }

    public CsInquiryDto toDto(Profile profile) {
        return CsInquiryDto.of(
                title,
                content,
                profile,
                category
        );
    }

}
