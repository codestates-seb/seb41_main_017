package com.codestates.culinari.customercenter.dto.response;

import com.codestates.culinari.customercenter.constant.ProcessStatus;
import com.codestates.culinari.customercenter.dto.CsInquiryDto;

public record CsInquiryResponse(
        Long id,
        String title,
        String content,
        ProcessStatus processStatus
) {

    public static CsInquiryResponse from(CsInquiryDto csInquiryDto) {
        return new CsInquiryResponse(
                csInquiryDto.id(),
                csInquiryDto.title(),
                csInquiryDto.content(),
                csInquiryDto.processStatus()
        );
    }
}
