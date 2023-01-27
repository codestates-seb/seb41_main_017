package com.codestates.culinari.user.service.impl;

import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.dto.UserDto;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.entitiy.Users;
import com.codestates.culinari.user.repository.ProfileRepository;
import com.codestates.culinari.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 프로필")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("회원가입폼을 정상적으로 입력하면 회원가입에 성공한다.")
    @Test
    void givenSignUpDto_whenSavingUser_thenUserDto() {
        //given
        Profile profile = createProfile(1L);
        Users user = createUser();
        SignUpDto signUpDto = createSignUpDto();

        given(passwordEncoder.encode(anyString()))
                .willReturn("");
        given(profileRepository.save(any(Profile.class)))
                .willReturn(profile);
        given(userRepository.save(any(Users.class)))
                .willReturn(user);

        //when
        UserDto userDto = userService.createUser(signUpDto);

        //then
        then(passwordEncoder).should().encode(anyString());
        then(profileRepository).should().save(any(Profile.class));
        then(userRepository).should().save(any(Users.class));
        assertThat(userDto)
                .hasFieldOrPropertyWithValue("username", signUpDto.username())
                .hasFieldOrPropertyWithValue("password", signUpDto.password());
    }

    @DisplayName("입력한 아이디가 중복 되지 않는다면 에러가 발생하지 않는다.")
    @Test
    void givenUsername_whenGetUser_thenNothing() {
        //given
        String username = "id";

        given(userRepository.findByUsername(anyString()))
                .willReturn(Optional.empty());

        //when
        userService.verifyExistsUsername(username);

        //then
        then(userRepository).should().findByUsername(anyString());
    }

    @DisplayName("입력한 아이디가 중복 한다면, 에러가 발생한다.")
    @Test
    void givenUsername_whenGetUser_thenThrowsException() {
        //given
        String username = "id";
        Users user = createUser();

        given(userRepository.findByUsername(anyString()))
                .willReturn(Optional.of(user));

        //when
        Throwable throwable = catchThrowable(() -> userService.verifyExistsUsername(username));

        //then
        assertThat(throwable)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Username exist");
        then(userRepository).should().findByUsername(anyString());
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

    private static Users createUser() {
        return Users.of(
                1L,
                "id",
                "password",
                List.of(),
                null
        );
    }

    private static SignUpDto createSignUpDto(){
        return SignUpDto.of(
                "id",
                "password",
                "test",
                "test@test.com",
                "010-1111-2222",
                "서울시",
                GenderType.MAN,
                LocalDate.now()
        );
    }
}
