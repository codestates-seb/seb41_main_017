package com.codestates.culinari.customercenter.dto.response;

import com.codestates.culinari.customercenter.entity.CsInquiryComment;

import java.time.format.DateTimeFormatter;

public record CsInquiryCommentResponse(
        Long id,
        String writer,
        String content,
        String createdAt
) {
    public static CsInquiryCommentResponse from(CsInquiryComment csInquiryComment) {
        return new CsInquiryCommentResponse(
                csInquiryComment.getId(),
                csInquiryComment.getProfile().getName(),
                csInquiryComment.getContent(),
                csInquiryComment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }
}
