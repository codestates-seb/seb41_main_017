package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.customercenter.dto.request.CsNoticePost;
import com.codestates.culinari.customercenter.dto.response.CsNoticeResponse;
import com.codestates.culinari.customercenter.service.CustomerNoticeService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 공지사항")
@Import(TestSecurityConfig.class)
@WebMvcTest(CustomerNoticeController.class)
class CustomerNoticeControllerTest {

    private final MockMvc mvc;
    @MockBean
    private CustomerNoticeService customerNoticeService;
    @MockBean
    private PaginationService paginationService;

    CustomerNoticeControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[POST] 공지사항 등록  - 정상호출")
    @Test
    void givenCsNoticeInfo_whenSavingCsNotice_thenCsNotice() throws Exception {
        //given
        String requestBody = """
                {
                    "title" : "test title",
                    "content" : "test content"
                }
                """;

        willDoNothing().given(customerNoticeService).createNotice(any(CsNoticePost.class));

        //when
        ResultActions actions =
                mvc.perform(post("/board/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        actions.andExpect(status().isResetContent());
    }

    @DisplayName("[GET] 공지사항 리스트 보기(페이지)  - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenCsNoticePageResponse() throws Exception {
        //given
        given(customerNoticeService.readNoticePage(any(Pageable.class)))
                .willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt()))
                .willReturn(List.of(1, 2, 3, 4, 5));

        //when
        ResultActions actions =
                mvc.perform(get("/board/notice")
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.pageInfo.totalElements").value(0));
        then(customerNoticeService.readNoticePage(any(Pageable.class)));
        then(paginationService.getPaginationBarNumbers(anyInt(), anyInt()));

    }

    @DisplayName("[GET] 공지사항 보기(단일건)  - 정상호출")
    @Test
    void givenCsNoticeId_whenRequesting_thenCsNotice() throws Exception {
        //given
        Long noticeId = 1L;
        CsNoticeResponse csNoticeResponse = createCsNoticeResponse();

        given(customerNoticeService.readNotice(anyLong()))
                .willReturn(csNoticeResponse);

        //when
        ResultActions actions =
                mvc.perform(get("/board/notice/{notice-id}", noticeId)
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(csNoticeResponse.title()));
    }

    private CsNoticeResponse createCsNoticeResponse() {
        return CsNoticeResponse.of(
                "test title",
                "test content",
                LocalDateTime.now(),
                "test user"
        );
    }
}
