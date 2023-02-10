package com.codestates.culinari.user.dto.request;

import jakarta.validation.constraints.Pattern;

public record UserPatchPasswordRequest(
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,20}$") //숫자, 알파벳, 특수문자(!@#$%^&*) 포함 8자 이상 20자 이하
        String password
) {
    public static UserPatchPasswordRequest of(String password) {
        return new UserPatchPasswordRequest(
                password
        );
    }
}
