package com.codestates.culinari.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignInDto(
        @NotBlank(message = "아이디 입력은 필수입니다.")
        @Length(max = 30, message = "아이디 길이는 30자 를 넘으면 안됩니다.")
        String username,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        @Length(max = 255, message = "비밀번호 길이는 255자 를 넘으면 안됩니다.")
        String password
) {
}
