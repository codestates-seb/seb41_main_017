package com.codestates.culinari.user.dto;

import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProfileDto(
        Long id,
        String name,
        String email,
        String phoneNumber,
        BigDecimal point,
        String address,
        GenderType gender,
        LocalDate birthDate
) {

    public static ProfileDto of(SignUpDto signUpDto) {
        return new ProfileDto(
                null,
                signUpDto.name(),
                signUpDto.email(),
                signUpDto.phoneNumber(),
                null,
                signUpDto.address(),
                signUpDto.genderType(),
                signUpDto.birthDate()
        );
    }
    public static ProfileDto from(Profile entity){
        return new ProfileDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getPoint(),
                entity.getAddress(),
                entity.getGender(),
                entity.getBirthDate()
        );
    }

    public Profile toEntity() {
        return Profile.of(
                name,
                email,
                phoneNumber,
                point,
                address,
                gender,
                birthDate
        );
    }
}
