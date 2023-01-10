package com.codestates.culinari.config.security.dto;

import lombok.Getter;

public record CustomPrincipal(
        @Getter String username,
        Long userId,
        Long profileId
) {
    public static CustomPrincipal of(String username, Long userId, Long profileId) {
        return new CustomPrincipal(username, userId, profileId);
    }
}
