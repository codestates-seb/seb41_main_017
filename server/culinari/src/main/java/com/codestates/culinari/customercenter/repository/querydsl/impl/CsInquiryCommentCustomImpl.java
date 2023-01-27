package com.codestates.culinari.customercenter.repository.querydsl.impl;

import com.codestates.culinari.customercenter.entity.CsInquiryComment;
import com.codestates.culinari.customercenter.entity.QCsInquiryComment;
import com.codestates.culinari.customercenter.repository.querydsl.CsInquiryCommentCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CsInquiryCommentCustomImpl extends QuerydslRepositorySupport implements CsInquiryCommentCustom {

    public CsInquiryCommentCustomImpl() {
        super(CsInquiryComment.class);
    }

    @Override
    public Page<CsInquiryComment> findAllByInquiryId(Pageable pageable, Long inquiryId) {
        QCsInquiryComment a = QCsInquiryComment.csInquiryComment;

        List<CsInquiryComment> csInquiryComments = from(a)
                .select(a)
                .where(a.csInquiry.id.eq(inquiryId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = from(a)
                .select(a.count())
                .where(a.csInquiry.id.eq(inquiryId))
                .fetchOne();

        return new PageImpl<>(csInquiryComments, pageable, count);
    }
}
