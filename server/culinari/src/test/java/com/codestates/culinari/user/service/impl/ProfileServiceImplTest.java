package com.codestates.culinari.user.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.request.ProfilePatchRequest;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 프로필")
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @InjectMocks
    private ProfileServiceImpl profileService;
    @Mock
    private ProfileRepository profileRepository;


    @DisplayName("로그인이 되어있는 상태라면, 마이페이지를 열람할 수 있다.")
    @Test
    void givenPrincipal_whenGetProfile_thenReturnProfileMyPageResponseDto() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Profile profile = createProfile(1L);

        given(profileRepository.findById(anyLong()))
                .willReturn(Optional.of(profile));

        //when
        ProfileMyPageResponseDto profileMyPageResponseDto = profileService.readProfile(principal);

        //then
        assertThat(profileMyPageResponseDto)
                .hasFieldOrPropertyWithValue("name", principal.username());
        then(profileRepository).should().findById(anyLong());
    }

    @DisplayName("로그인이 되어있는 상태라면, 사용자 정보와 리뷰리스트들을 열람할 수 있다.")
    @Test
    void givenPrincipal_whenGetProfile_thenReturnProfileDto() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Profile profile = createProfile(1L);

        given(profileRepository.findById(anyLong()))
                .willReturn(Optional.of(profile));

        //when
        ProfileDto profileDto = profileService.readMyPageReview(principal);

        //then
        assertThat(profileDto)
                .hasFieldOrPropertyWithValue("name", principal.username());
        then(profileRepository).should().findById(anyLong());
    }

    @DisplayName("입력한 이메일이 중복 되지 않는다면 에러가 발생하지 않는다.")
    @Test
    void givenEmail_whenGetProfile_thenNothing() {
        //given
        String email = "test@test.com";

        given(profileRepository.findByEmail(anyString()))
                .willReturn(Optional.empty());

        //when
        profileService.verifyExistsEmail(email);

        //then
        then(profileRepository).should().findByEmail(anyString());
    }

    @DisplayName("입력한 이메일이 중복한다면, 에러가 발생한다.")
    @Test
    void givenEmail_whenGetProfile_thenThrowsException() {
        //given
        String email = "email@email.com";
        Profile profile = createProfile(1L);

        given(profileRepository.findByEmail(anyString()))
                .willReturn(Optional.of(profile));

        //when
        Throwable throwable = catchThrowable(() -> profileService.verifyExistsEmail(email));

        //then
        assertThat(throwable)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Email exist");
        then(profileRepository).should().findByEmail(anyString());
    }

    @DisplayName("로그인을 한 상태에서 프로필을 수정폼을 입력하면, 내 프로필을 수정한다.")
    @Test
    void givenModifiedProfileInfo_whenUpdatingProfile_thenUpdateProfile() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Profile profile = createProfile(1L);
        ProfilePatchRequest profilePatchRequest = createProfilePatchRequest();

        given(profileRepository.findById(anyLong()))
                .willReturn(Optional.of(profile));

        //when
        ProfileDto profileDto = profileService.updateProfile(principal, profilePatchRequest);

        //then
        assertThat(profileDto)
                .hasFieldOrPropertyWithValue("name", principal.username())
                .hasFieldOrPropertyWithValue("email", "email@email.com");
        then(profileRepository).should().findById(anyLong());
    }

    private ProfilePatchRequest createProfilePatchRequest() {
        return ProfilePatchRequest.of(
                "사용자 명",
                "email@email.com",
                "010-0000-0000",
                "주소",
                GenderType.MAN,
                LocalDate.now()
        );
    }

    private static Profile createProfile(Long profileId) {
        Profile profile = Profile.of(
                "사용자 명",
                "email@email.com",
                "010-0000-0000",
                BigDecimal.valueOf(0L),
                "주소",
                GenderType.MAN,
                LocalDate.now()
        );
        ReflectionTestUtils.setField(profile, "id", profileId);

        return profile;
    }
}
