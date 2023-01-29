package com.codestates.culinari.customercenter.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryResponse;
import com.codestates.culinari.customercenter.service.CustomerInquiryService;
import com.codestates.culinari.pagination.service.PaginationService;
import config.TestSecurityConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 1:1문의")
@Import(TestSecurityConfig.class)
@WebMvcTest(CustomerInquiryController.class)
class CustomerInquiryControllerTest {

    private final MockMvc mvc;
    @MockBean
    private CustomerInquiryService customerInquiryService;
    @MockBean
    private PaginationService paginationService;

    static Authentication auth;

    CustomerInquiryControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeAll
    static void init() {
        auth = new UsernamePasswordAuthenticationToken(createPrincipal("test", 1L, 1L), null);
    }

    @DisplayName("[POST] 1:1문의 등록 - 정상호출")
    @Test
    void givenCsInquiryInfo_whenSavingCsInquiry_thenNothing() throws Exception {
        //given
        String requestBody = """
                {
                    "title" : "test title",
                    "content" : "test content"
                }
                """;

        willDoNothing().given(customerInquiryService).createEnquire(
                any(CustomPrincipal.class), any(CsInquiryPost.class));

        //when
        ResultActions actions =
                mvc.perform(post("/board/inquiry")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        actions.andExpect(status().isResetContent());
    }

    @DisplayName("[GET] 내가 등록한 1:1 문의들 보기(페이지) - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenCsInquiryPageResponse() throws Exception {
        //given
        given(customerInquiryService.readInquiriePage(any(CustomPrincipal.class), any(Pageable.class)))
                .willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt()))
                .willReturn(List.of(1, 2, 3, 4, 5));

        //when
        ResultActions actions =
                mvc.perform(get("/board/inquiry")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.pageInfo.totalElements").value(0));
        then(customerInquiryService.readInquiriePage(any(CustomPrincipal.class), any(Pageable.class)));
        then(paginationService.getPaginationBarNumbers(anyInt(), anyInt()));
    }

    @DisplayName("[GET] 내가 등록한 1:1 문의 보기(단일건)  - 정상호출")
    @Test
    void givenCsInquiryId_whenRequesting_thenCsInquiryResponse() throws Exception {
        //given
        CsInquiryResponse csInquiryResponse = createCsInquiryResponse();
        Long inquiryId = 1L;

        given(customerInquiryService.readInquire(any(CustomPrincipal.class), anyLong()))
                .willReturn(csInquiryResponse);

        //when
        ResultActions actions =
                mvc.perform(get("/board/inquiry/{inquiry-id}", inquiryId)
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.title").value("test_title"))
                .andExpect(jsonPath("$.data.content").value("test_content"));
        then(customerInquiryService).should().readInquire(any(CustomPrincipal.class), anyLong());
    }

    @DisplayName("[DELETE] 내가 등록한 1:1 문의 삭제  - 정상호출")
    @Test
    void givenCsInquiryId_whenDeletingCsInquiry_thenNothing() throws Exception {
        //given
        Long inquiryId = 1L;

        willDoNothing().given(customerInquiryService).deleteEnquire(any(CustomPrincipal.class), anyLong());

        //when
        ResultActions actions =
                mvc.perform(delete("/board/inquiry/{inquiry-id}", inquiryId)
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isNoContent());
        then(customerInquiryService).should().deleteEnquire(any(CustomPrincipal.class), anyLong());
    }

    @DisplayName("[PATCH] 내가 등록한 1:1 문의 수정  - 정상호출")
    @Test
    void givenCsInquiryId_whenUpdatingCsInquiry_thenCsInquiry() throws Exception {
        //given
        Long inquiryId = 1L;
        String requestBody = """
                {
                    "title" : "test title1111",
                    "content" : "test content1111"
                }
                """;

//        willDoNothing().given(customerInquiryService).updateEnquire(any(CustomPrincipal.class), anyLong(), any(CsInquiryPost.class));

        //when
        ResultActions actions =
                mvc.perform(patch("/board/inquiry/{inquiry-id}", inquiryId)
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        actions.andExpect(status().isResetContent());
    }

    private CsInquiryResponse createCsInquiryResponse() {
        return null;
//        return CsInquiryResponse.of(
//                1L,
//                "test_title",
//                "test_content",
//                ProcessStatus.STAND_BY
//        );
    }
}
