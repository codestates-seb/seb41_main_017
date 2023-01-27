package com.codestates.culinari.config.security.dto;

import com.codestates.culinari.user.constant.RoleType;
import com.codestates.culinari.user.entitiy.UserRole;
import com.codestates.culinari.user.entitiy.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public record CustomUserDetails(
        Long userId,
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        Long profileId
) implements UserDetails {

    public static CustomUserDetails of(Long userId, String username, String password, List<UserRole> userRoles, Long profileId) {
        return new CustomUserDetails(
                userId,
                username,
                password,
                userRoles.stream()
                        .map(UserRole::getRole)
                        .map(RoleType::getType)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                profileId
        );
    }

    public static CustomUserDetails from(Users users) {
        return CustomUserDetails.of(
                users.getId(),
                users.getUsername(),
                users.getPassword(),
                users.getUserRoles(),
                users.getProfile().getId()
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
