package com.codestates.culinari.user.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;

public interface ProfileService {

    ProfileMyPageResponseDto readProfile(CustomPrincipal customPrincipal);

    void verifyExistsEmail(String email);

}
