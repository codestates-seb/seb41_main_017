package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.customercenter.dto.request.CsFrequentlyAskedQuestionRequest;
import com.codestates.culinari.customercenter.dto.response.CsFrequentlyAskedQuestionResponse;
import com.codestates.culinari.customercenter.entity.CsFrequentlyAskedQuestion;
import com.codestates.culinari.customercenter.repository.CsFrequentlyAskedQuestionRepository;
import com.codestates.culinari.customercenter.service.impl.CustomerFrequentlyAskedQuestionImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 고객센터_자주묻는 질문")
@ExtendWith(MockitoExtension.class)
class CustomerFrequentlyAskedQuestionServiceTest {

    @InjectMocks
    private CustomerFrequentlyAskedQuestionImpl csFAQService;
    @Mock
    private CsFrequentlyAskedQuestionRepository csFAQRepository;

    @DisplayName("카테고리 필터를 적용하지 않으면, 자주묻는 질문 전체 조회한다")
    @Test
    void givenNothing_whenFindAll_thenCsFAQPage() {
        //given
        String category = null;
        CsFrequentlyAskedQuestion csFrequentlyAskedQuestion = createCsFrequentlyAskedQuestion(1L);

        given(csFAQRepository.findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(csFrequentlyAskedQuestion)));

        //when
        Page<CsFrequentlyAskedQuestionResponse> csFAQResponsePage =
                csFAQService.readFrequentlyAskedQuestionPage(category, PageRequest.of(0, 10));

        //then
        assertThat(csFAQResponsePage).isNotEmpty();
        then(csFAQRepository).should().findAll(any(Pageable.class));
    }

    @DisplayName("카테고리 필터적용하면, 해당 카테고리만 조회한다")
    @Test
    void givenCategory_whenFindCategory_thenCsFAQPage() {
        //given
        String category = "컬리패스";
        CsFrequentlyAskedQuestion csFrequentlyAskedQuestion = createCsFrequentlyAskedQuestion(1L);

        given(csFAQRepository.findCategoryFilter(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(csFrequentlyAskedQuestion)));

        //when
        Page<CsFrequentlyAskedQuestionResponse> csFAQResponsePage =
                csFAQService.readFrequentlyAskedQuestionPage(category, PageRequest.of(0, 10));

        //then
        assertThat(csFAQResponsePage).isNotEmpty();
        then(csFAQRepository).should().findCategoryFilter(anyString(), any(Pageable.class));
    }

    @DisplayName("자주묻는 질문 양식을 작성하면, 게시물이 생성된다.")
    @Test
    void givencsFAQRequest_whenSavingCsFAQ_thenNothing() {
        //given
        CsFrequentlyAskedQuestionRequest csFAQRequest = createCsFrequentlyAskedQuestionRequest();

        given(csFAQRepository.save(any(CsFrequentlyAskedQuestion.class)))
                .willReturn(null);

        //when
        csFAQService.createCsFrequentlyAskedQuestion(csFAQRequest);

        //then
        then(csFAQRepository).should().save(any(CsFrequentlyAskedQuestion.class));
    }

    private CsFrequentlyAskedQuestionRequest createCsFrequentlyAskedQuestionRequest() {
        return CsFrequentlyAskedQuestionRequest.of(
                "test title",
                "test content",
                "컬리패스"
        );
    }

    private CsFrequentlyAskedQuestion createCsFrequentlyAskedQuestion(Long id){
        CsFrequentlyAskedQuestion csFrequentlyAskedQuestion = CsFrequentlyAskedQuestion.of(
                "test title",
                "content",
                "category"
        );

        ReflectionTestUtils.setField(csFrequentlyAskedQuestion, "id", id);
        return csFrequentlyAskedQuestion;
    }
}
