package com.codestates.culinari.customercenter.repository.querydsl.impl;

import com.codestates.culinari.customercenter.entity.CsFrequentlyAskedQuestion;
import com.codestates.culinari.customercenter.entity.QCsFrequentlyAskedQuestion;
import com.codestates.culinari.customercenter.repository.querydsl.CsFrequentlyAskedQuestionCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CsFrequentlyAskedQuestionCustomImpl extends QuerydslRepositorySupport implements CsFrequentlyAskedQuestionCustom {

    public CsFrequentlyAskedQuestionCustomImpl() {
        super(CsFrequentlyAskedQuestion.class);
    }

    @Override
    public Page<CsFrequentlyAskedQuestion> findCategoryFilter(String category, Pageable pageable) {
        QCsFrequentlyAskedQuestion a = QCsFrequentlyAskedQuestion.csFrequentlyAskedQuestion;

        List<CsFrequentlyAskedQuestion> frequentlyAskedQuestions = from(a)
                .select(a)
                .where(a.category.containsIgnoreCase(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = from(a)
                .select(a.count())
                .where(a.category.containsIgnoreCase(category))
                .fetchOne();

        return new PageImpl<>(frequentlyAskedQuestions, pageable, count);
    }
}
