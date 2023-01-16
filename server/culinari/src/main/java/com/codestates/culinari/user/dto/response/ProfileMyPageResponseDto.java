package com.codestates.culinari.user.dto.response;

import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProfileMyPageResponseDto(
        String name,
        String email,
        String phoneNumber,
        BigDecimal point,
        String address,
        GenderType gender,
        LocalDate birthDate
) {

    public static ProfileMyPageResponseDto of(String name, String email, String phoneNumber, BigDecimal point, String address, GenderType gender, LocalDate birthDate) {
        return new ProfileMyPageResponseDto(
                name,
                email,
                phoneNumber,
                point,
                address,
                gender,
                birthDate
        );
    }

    public static ProfileMyPageResponseDto from(Profile profile){
        return new ProfileMyPageResponseDto(
                profile.getName(),
                profile.getEmail(),
                profile.getPhoneNumber(),
                profile.getPoint(),
                profile.getAddress(),
                profile.getGender(),
                profile.getBirthDate()
        );
    }
}
