package com.codestates.culinari.config.security.dto;

import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.entitiy.Profile;

public record CustomPrincipal(
        String username,
        Long userId,
        Long profileId
) {
    public static CustomPrincipal of(String username, Long userId, Long profileId) {
        return new CustomPrincipal(username, userId, profileId);
    }
}
