package com.codestates.culinari.user.service.Impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import com.codestates.culinari.user.repository.ProfileRepository;
import com.codestates.culinari.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    @Override
    public ProfileMyPageResponseDto readProfile(CustomPrincipal customPrincipal) {
        return profileRepository.findById(customPrincipal.profileId())
                .map(ProfileMyPageResponseDto::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public void verifyExistsEmail(String email) {
        if(profileRepository.findByEmail(email).isPresent()){
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);
        }
    }
}
