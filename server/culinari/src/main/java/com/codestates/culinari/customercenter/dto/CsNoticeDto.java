package com.codestates.culinari.customercenter.dto;

import com.codestates.culinari.customercenter.entity.CsNotice;

import java.time.LocalDateTime;

public record CsNoticeDto(
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy
) {
    public static CsNoticeDto of(String title, String content) {
        return new CsNoticeDto(
                title,
                content,
                null,
                null
        );
    }

    public static CsNoticeDto from(CsNotice csNotice) {
        return new CsNoticeDto(
                csNotice.getTitle(),
                csNotice.getContent(),
                csNotice.getCreatedAt(),
                csNotice.getCreatedBy()
        );
    }

    public CsNotice toEntity() {
        return CsNotice.of(
                null,
                title,
                content
        );
    }
}
