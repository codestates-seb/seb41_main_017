package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.customercenter.dto.request.CsNoticePost;
import com.codestates.culinari.customercenter.dto.response.CsNoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerNoticeService {
    void createNotice(CsNoticePost csNoticePost);

    Page<CsNoticeResponse> readNoticePage(Pageable pageable);

    CsNoticeResponse readNotice(Long inquiryId);
}
