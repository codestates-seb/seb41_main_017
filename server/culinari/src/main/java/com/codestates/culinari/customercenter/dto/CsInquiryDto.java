package com.codestates.culinari.customercenter.dto;

import com.codestates.culinari.customercenter.constant.ProcessStatus;
import com.codestates.culinari.customercenter.dto.request.CsInquiryRequest;
import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.user.entitiy.Profile;

import java.time.LocalDateTime;

public record CsInquiryDto(
        Long id,
        String title,
        String content,
        Profile profile,
        ProcessStatus processStatus
) {

    public static CsInquiryDto of(String title, String content, Profile profile) {
        return new CsInquiryDto(
                null,
                title,
                content,
                profile,
                ProcessStatus.STAND_BY
        );
    }

    public static CsInquiryDto from(CsInquiry csInquiry) {
        return new CsInquiryDto(
                csInquiry.getId(),
                csInquiry.getTitle(),
                csInquiry.getContent(),
                csInquiry.getProfile(),
                csInquiry.getProcessStatus()
        );
    }

    public CsInquiry toEntity() {
        return CsInquiry.of(
                id,
                title,
                content,
                profile,
                processStatus
        );
    }
}
