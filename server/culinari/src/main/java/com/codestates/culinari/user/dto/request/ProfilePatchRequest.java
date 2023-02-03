package com.codestates.culinari.user.dto.request;

import com.codestates.culinari.user.constant.GenderType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ProfilePatchRequest(
        @Length(max = 30, message = "이름 길이는 30자 를 넘으면 안됩니다.")
        String name,

        @Length(max = 30, message = "아이디 길이는 30자 를 넘으면 안됩니다.")
        @Email(message = "이메일 형식을 지켜야 합니다.")
        String email,

        @Length(max = 15, message = "휴대폰번호 길이는 15자 를 넘으면 안됩니다.")
        String phoneNumber,

        GenderType genderType,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @PastOrPresent(message = "미래 날짜로 수정 불가합니다.")
        LocalDate birthDate
) {
    public static ProfilePatchRequest of(String name, String email, String phoneNumber, GenderType genderType, LocalDate birthDate) {
        return new ProfilePatchRequest(
                name,
                email,
                phoneNumber,
                genderType,
                birthDate
        );
    }
}
