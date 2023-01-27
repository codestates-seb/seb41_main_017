package com.codestates.culinari.customercenter.dto.response;

import com.codestates.culinari.customercenter.constant.ProcessStatus;
import com.codestates.culinari.customercenter.entity.CsInquiry;

import java.time.format.DateTimeFormatter;

public record CsInquiryResponse(
        Long id,
        String title,
        String content,
        String category,
        ProcessStatus processStatus,
        String createdAt
) {
//    테스트 코드 생성 코드.
//    public static CsInquiryResponse of(Long id, String title, String content,String category, ProcessStatus processStatus) {
//        return new CsInquiryResponse(
//                id,
//                title,
//                content,
//                category,
//                processStatus
//        );
//    }

    public static CsInquiryResponse from(CsInquiry csInquiry) {
        return new CsInquiryResponse(
                csInquiry.getId(),
                csInquiry.getTitle(),
                csInquiry.getContent(),
                csInquiry.getCategory(),
                csInquiry.getProcessStatus(),
                csInquiry.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE)
        );
    }
}
