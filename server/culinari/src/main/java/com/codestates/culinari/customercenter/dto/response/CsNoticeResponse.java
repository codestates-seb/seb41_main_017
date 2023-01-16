package com.codestates.culinari.customercenter.dto.response;

import com.codestates.culinari.customercenter.dto.CsNoticeDto;

import java.time.LocalDateTime;

public record CsNoticeResponse(
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy
) {

    public static CsNoticeResponse of(String title, String content, LocalDateTime createdAt, String createdBy) {
        return new CsNoticeResponse(
                title,
                content,
                createdAt,
                createdBy
        );
    }

    public static CsNoticeResponse from(CsNoticeDto csNoticeDto) {
        return new CsNoticeResponse(
                csNoticeDto.title(),
                csNoticeDto.content(),
                csNoticeDto.createdAt(),
                csNoticeDto.createdBy()
        );
    }
}
