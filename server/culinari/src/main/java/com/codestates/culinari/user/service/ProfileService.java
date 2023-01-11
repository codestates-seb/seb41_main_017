package com.codestates.culinari.user.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfileService {

    ProfileMyPageResponseDto readProfile(CustomPrincipal customPrincipal);

    @Transactional(readOnly = true)
    ProfileDto readProfileInquiry(CustomPrincipal customPrincipal);

    void verifyExistsEmail(String email);

}
