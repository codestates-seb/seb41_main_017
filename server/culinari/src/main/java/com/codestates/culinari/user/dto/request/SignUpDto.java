package com.codestates.culinari.user.dto.request;

import com.codestates.culinari.user.constant.GenderType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record SignUpDto(
        @NotBlank(message = "아이디 입력은 필수입니다.")
        @Length(max = 30, message = "아이디 길이는 30자 를 넘으면 안됩니다.")
        String username,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,20}$") //숫자, 알파벳, 특수문자(!@#$%^&*) 포함 8자 이상 20자 이하
        String password,

        @NotBlank(message = "이름 입력은 필수입니다.")
        @Length(max = 30, message = "이름 길이는 30자 를 넘으면 안됩니다.")
        String name,

        @NotBlank(message = "이메일 입력은 필수입니다.")
        @Length(max = 30, message = "아이디 길이는 30자 를 넘으면 안됩니다.")
        @Email(message = "이메일 형식을 지켜야 합니다.")
        String email,

        @NotBlank(message = "휴대폰번호 입력은 필수입니다.")
        @Length(max = 15, message = "휴대폰번호 길이는 15자 를 넘으면 안됩니다.")
        String phoneNumber,

        @NotBlank(message = "주소 입력은 필수입니다.")
        @Length(max = 100, message = "주소 길이는 100자 를 넘으면 안됩니다.")
        String address,

        @NotNull(message = "성별 입력은 필수입니다.")
        GenderType genderType,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @PastOrPresent(message = "미래에서 오셨나요???? 미래에서 오신분은 사용 불가입니다.")
        LocalDate birthDate
) {

        public static SignUpDto of(String username, String password, String name, String email, String phoneNumber, String address, GenderType genderType, LocalDate birthDate) {
                return new SignUpDto(
                        username,
                        password,
                        name,
                        email,
                        phoneNumber,
                        address,
                        genderType,
                        birthDate
                );
        }
}
