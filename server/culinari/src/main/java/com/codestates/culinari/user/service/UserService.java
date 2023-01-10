package com.codestates.culinari.user.service;

import com.codestates.culinari.user.dto.UserDto;
import com.codestates.culinari.user.dto.request.SignUpDto;

public interface UserService {

    // FIXME : return 타입 UserDto는 테스트용, 실 서비스 시작시 void 로 바꿀예정
    UserDto createUser(SignUpDto signUpDto);

    void verifyExistsUsername(String username);
}
