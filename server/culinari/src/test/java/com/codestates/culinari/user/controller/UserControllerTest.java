package com.codestates.culinari.user.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.UserDto;
import com.codestates.culinari.user.dto.request.ProfilePatchRequest;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import com.codestates.culinari.user.service.ProfileService;
import com.codestates.culinari.user.service.UserService;
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
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("컨트롤러 - 유저, 프로필")
@Import(TestSecurityConfig.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    private final MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private ProfileService profileService;

    UserControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET] 유저정보 조회 - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenReturnProfileDto() throws Exception {
        //given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("test", 1L, 1L), null);
        ProfileMyPageResponseDto profileResponse = createProfileMyPageResponse();

        given(profileService.readProfile(any(CustomPrincipal.class)))
                .willReturn(profileResponse);

        //when
        ResultActions actions =
                mvc.perform(get("/users")
                        .with(authentication(auth))
                        .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(profileResponse.name()))
                .andExpect(jsonPath("$.data.email").value(profileResponse.email()))
                .andExpect(jsonPath("$.data.phoneNumber").value(profileResponse.phoneNumber()));
        then(profileService).should().readProfile(any(CustomPrincipal.class));
    }

    @DisplayName("[PATCH] 유저정보 수정 - 정상호출")
    @Test
    void givenProfileInfo_whenRequesting_thenReturnProfileDto() throws Exception {
        //given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("test", 1L, 1L), null);
        ProfileDto profileResponse = createProfileDto();
        String requestBody = """
                {
                  "name" : "test",
                  "email": "test@test.com",
                  "phoneNumber": "010-1111-2222",
                  "address": "평양시",
                  "genderType": "남성",
                  "birthDate": "1900-01-01"
                }
                """;

        given(profileService.updateProfile(any(CustomPrincipal.class), any(ProfilePatchRequest.class)))
                .willReturn(profileResponse);

        //when
        ResultActions actions =
                mvc.perform(patch("/users")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value(profileResponse.name()))
                .andExpect(jsonPath("$.data.email").value(profileResponse.email()))
                .andExpect(jsonPath("$.data.phoneNumber").value(profileResponse.phoneNumber()));
        then(profileService).should().updateProfile(any(CustomPrincipal.class), any(ProfilePatchRequest.class));
    }

    @DisplayName("[POST] 회원가입 - 정상호출")
    @Test
    void givenUserInfo_whenRequesting_thenReturnUserDto() throws Exception {
        //given
        UserDto userResponse = createUserDto();
        String requestBody = """
                {
                   "username": "id",
                   "password": "password",
                   "name" : "test",
                   "email": "test@test.com",
                   "phoneNumber": "010-1111-2222",
                   "address": "서울시",
                   "genderType": "남성",
                   "birthDate": "1997-01-01"
                 }
                """;

        given(userService.createUser(any(SignUpDto.class)))
                .willReturn(userResponse);

        //when
        ResultActions actions =
                mvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        //then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.username").value(userResponse.username()))
                .andExpect(jsonPath("$.data.password").value(userResponse.password()));
        then(userService).should().createUser(any(SignUpDto.class));
    }

    @DisplayName("[GET] 아이디 중복검사 - 정상호출")
    @Test
    void usernameDuplicationCheck() throws Exception {
        //given
        willDoNothing().given(userService).verifyExistsUsername(anyString());

        //when
        ResultActions actions =
                mvc.perform(get("/users/username-check")
                        .param("username", "test")
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk());
        then(userService).should().verifyExistsUsername(anyString());
    }

    @DisplayName("[GET] 이메일 중복검사 - 정상호출")
    @Test
    void emailDuplicationCheck() throws Exception {
        //given
        willDoNothing().given(profileService).verifyExistsEmail(anyString());

        //when
        ResultActions actions =
                mvc.perform(get("/users/email-check")
                        .param("email", "test@test.com")
                        .contentType(MediaType.APPLICATION_JSON));


        //then
        actions.andExpect(status().isOk());
        then(profileService).should().verifyExistsEmail(anyString());
    }


    private ProfileMyPageResponseDto createProfileMyPageResponse() {
        return ProfileMyPageResponseDto.of(
                "test",
                "test@test.com",
                "010-1111-2222",
                new BigDecimal(0),
                "평양시",
                GenderType.MAN,
                LocalDate.now()
        );
    }

    private ProfileDto createProfileDto() {
        return ProfileDto.of(
                1L,
                "test",
                "test@test.com",
                "010-1111-2222",
                new BigDecimal(0),
                "평양시",
                GenderType.MAN,
                LocalDate.now()
        );
    }


    private UserDto createUserDto() {
        return UserDto.of(
                1L,
                "id",
                "password",
                null,
                null
        );
    }
}
