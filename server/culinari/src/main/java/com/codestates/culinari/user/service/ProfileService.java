package com.codestates.culinari.user.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.request.ProfilePatchRequest;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface ProfileService {

    ProfileMyPageResponseDto readProfile(CustomPrincipal customPrincipal);

    @Transactional(readOnly = true)
    ProfileDto readMyPageReview(CustomPrincipal customPrincipal);

    void verifyExistsEmail(String email);

    ProfileDto updateProfile(CustomPrincipal customPrincipal, ProfilePatchRequest profilePatchRequest);
}
