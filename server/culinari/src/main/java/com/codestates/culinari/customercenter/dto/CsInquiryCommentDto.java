package com.codestates.culinari.customercenter.dto;

import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.customercenter.entity.CsInquiryComment;
import com.codestates.culinari.user.entitiy.Profile;

public record CsInquiryCommentDto(
        Long id,
        String title,
        String content,
        CsInquiry csInquiry,
        Profile profile
) {

    public static CsInquiryCommentDto of(String title, String content, CsInquiry csInquiry, Profile profile) {
        return new CsInquiryCommentDto(
                null,
                title,
                content,
                csInquiry,
                profile
        );
    }

    public CsInquiryComment toEntity() {
        return CsInquiryComment.of(
                title,
                content,
                csInquiry,
                profile
        );
    }
}
