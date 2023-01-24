package com.codestates.culinari.product.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.product.service.ProductService;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.service.ProfileService;
import config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static com.codestates.culinari.order.Stub.Stub.createProfile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 마이페이지 리뷰 리스트")
@Import(TestSecurityConfig.class)
@WebMvcTest(MypageController.class)
class MypageControllerTest {

    private final MockMvc mvc;

    @MockBean
    private ProfileService profileService;
    @MockBean
    private ProductService productService;
    @MockBean
    private  PaginationService paginationService;

    public MypageControllerTest(@Autowired MockMvc mvc){this.mvc = mvc;}

    @DisplayName("[GET] 마이 페이지 리뷰 리스트 조회 - 정상호출")
    @Test
    void givenNothing_whenRequestingGetReview_thenReturnReviewList() throws Exception {
        // Given
        long profileId = 1L;
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자", 1L, 1L), null);
        ProfileDto profile = ProfileDto.from(createProfile(profileId));

        given(profileService.readMyPageReview(any(CustomPrincipal.class))).willReturn(profile);

        // When&Then
        mvc.perform(get("/mypage/review")
                        .with(authentication(auth))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        then(profileService).should().readMyPageReview(any(CustomPrincipal.class));
    }
}