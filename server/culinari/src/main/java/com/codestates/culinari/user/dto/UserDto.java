package com.codestates.culinari.user.dto;

import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.entitiy.UserRole;
import com.codestates.culinari.user.entitiy.Users;

import java.util.ArrayList;
import java.util.List;

public record UserDto(
        Long id,
        String username,
        String password,
        List<UserRole> userRoles,
        Profile profile
) {

    public static UserDto of(Long id, String username, String password, List<UserRole> userRoles, Profile profile) {
        return new UserDto(
                id,
                username,
                password,
                userRoles,
                profile
        );
    }

    public static UserDto of(SignUpDto signUpDto, String encryptedPassword, Profile profile) {
        return new UserDto(
                null,
                signUpDto.username(),
                encryptedPassword,
                new ArrayList<>(),
                profile
        );
    }

    public static UserDto from(Users user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getUserRoles(),
                user.getProfile()
        );
    }

    public Users toEntity() {
        return Users.of(
                id,
                username,
                password,
                userRoles,
                profile
        );
    }
}
