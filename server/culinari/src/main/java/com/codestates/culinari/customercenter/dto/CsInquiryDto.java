package com.codestates.culinari.customercenter.dto;

import com.codestates.culinari.customercenter.constant.ProcessStatus;
import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.user.entitiy.Profile;

public record CsInquiryDto(
        Long id,
        String title,
        String content,
        String category,
        Profile profile,
        ProcessStatus processStatus
) {

    public static CsInquiryDto of(String title, String content, Profile profile, String category) {
        return new CsInquiryDto(
                null,
                title,
                content,
                category,
                profile,
                ProcessStatus.STAND_BY
        );
    }

    public static CsInquiryDto from(CsInquiry csInquiry) {
        return new CsInquiryDto(
                csInquiry.getId(),
                csInquiry.getTitle(),
                csInquiry.getContent(),
                csInquiry.getCategory(),
                csInquiry.getProfile(),
                csInquiry.getProcessStatus()
        );
    }

    public CsInquiry toEntity() {
        return CsInquiry.of(
                id,
                title,
                content,
                category,
                profile,
                processStatus
        );
    }
}
