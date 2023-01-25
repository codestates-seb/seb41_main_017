package com.codestates.culinari.user.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.user.dto.UserDto;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.dto.request.UserPatchPasswordRequest;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    // FIXME : return 타입 UserDto는 테스트용, 실 서비스 시작시 void 로 바꿀예정
    UserDto createUser(SignUpDto signUpDto);

    void updatePassword(CustomPrincipal customPrincipal, UserPatchPasswordRequest userPatchPasswordRequest);

    void verifyExistsUsername(String username);
}
