package com.codestates.culinari.customercenter.repository.querydsl;

import com.codestates.culinari.customercenter.entity.CsFrequentlyAskedQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CsFrequentlyAskedQuestionCustom {

    Page<CsFrequentlyAskedQuestion> findCategoryFilter(String searchValue, Pageable pageable);
}
