package com.codestates.culinari.user.dto.response;

import com.codestates.culinari.user.entitiy.Profile;

import java.math.BigDecimal;

public record ProfileMyPageResponseDto(
        String name,
        BigDecimal point
) {

    private static ProfileMyPageResponseDto of(String name, BigDecimal point) {
        return new ProfileMyPageResponseDto(name, point);
    }

    public static ProfileMyPageResponseDto from(Profile profile){
        return ProfileMyPageResponseDto.of(
                profile.getName(),
                profile.getPoint()
        );
    }
}
