package com.codestates.culinari.customercenter.repository;

import com.codestates.culinari.customercenter.entity.CsFrequentlyAskedQuestion;
import com.codestates.culinari.customercenter.repository.querydsl.CsFrequentlyAskedQuestionCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsFrequentlyAskedQuestionRepository extends JpaRepository<CsFrequentlyAskedQuestion, Long>, CsFrequentlyAskedQuestionCustom {

}
