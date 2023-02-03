package com.codestates.culinari.customercenter.dto;

import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.customercenter.entity.CsInquiryComment;
import com.codestates.culinari.user.entitiy.Profile;

public record CsInquiryCommentDto(
        Long id,
        String content,
        CsInquiry csInquiry,
        Profile profile
) {

    public static CsInquiryCommentDto of( String content, CsInquiry csInquiry, Profile profile) {
        return new CsInquiryCommentDto(
                null,
                content,
                csInquiry,
                profile
        );
    }

    public CsInquiryComment toEntity() {
        return CsInquiryComment.of(
                content,
                csInquiry,
                profile
        );
    }
}
