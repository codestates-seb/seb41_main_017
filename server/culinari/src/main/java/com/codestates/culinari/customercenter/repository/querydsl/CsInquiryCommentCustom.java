package com.codestates.culinari.customercenter.repository.querydsl;

import com.codestates.culinari.customercenter.entity.CsInquiryComment;
import com.codestates.culinari.customercenter.entity.QCsInquiryComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CsInquiryCommentCustom {

    Page<CsInquiryComment> findAllByInquiryId(Pageable pageable, Long inquiryId);
}
