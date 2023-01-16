package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.customercenter.dto.request.CsFrequentlyAskedQuestionRequest;
import com.codestates.culinari.customercenter.service.CustomerFrequentlyAskedQuestionService;
import com.codestates.culinari.pagination.service.PaginationService;
import config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 자주묻는 질문")
@Import(TestSecurityConfig.class)
@WebMvcTest(CsFrequentlyAskedQuestionController.class)
class CsFrequentlyAskedQuestionControllerTest {

    private final MockMvc mvc;
    @MockBean
    private CustomerFrequentlyAskedQuestionService csFaqService;
    @MockBean
    private PaginationService paginationService;

    CsFrequentlyAskedQuestionControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET] 자주묻는 질문 조회(페이지) - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenCsFAQResponse() throws Exception {
        //given
        given(csFaqService.readFrequentlyAskedQuestionPage(anyString(), any(Pageable.class)))
                .willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt()))
                .willReturn(List.of(1, 2, 3, 4, 5));

        //when
        ResultActions actions =
                mvc.perform(get("/board/faq")
                        .param("category", "컬리패스")
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk());
        then(csFaqService.readFrequentlyAskedQuestionPage(anyString(), any(Pageable.class)));
        then(paginationService.getPaginationBarNumbers(anyInt(), anyInt()));
    }

    @DisplayName("[POST] 자주묻는 질문 등록 - 정상호출")
    @Test
    void givenCsFAQRequest_whenSavingCsFAQ_thenNothing() throws Exception {
        //given
        String requestBody = """
                {
                   "title" : "test title",
                   "content" : "test content",
                   "category" : "컬리패스"
                }
                """;

        willDoNothing().given(csFaqService).createCsFrequentlyAskedQuestion(any(CsFrequentlyAskedQuestionRequest.class));

        //when
        ResultActions actions =
                mvc.perform(post("/board/faq")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        actions.andExpect(status().isResetContent());
    }
}
