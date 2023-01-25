package com.codestates.culinari.user.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.request.ProfilePatchRequest;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.entitiy.Users;
import com.codestates.culinari.user.repository.ProfileRepository;
import com.codestates.culinari.user.repository.UserRepository;
import com.codestates.culinari.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
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
    public ProfileDto readMyPageReview(CustomPrincipal customPrincipal) {
        return profileRepository.findById(customPrincipal.profileId())
                .map(ProfileDto::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public void verifyExistsEmail(String email) {
        if (profileRepository.findByEmail(email).isPresent()) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);
        }
    }

    @Override
    public ProfileDto updateProfile(CustomPrincipal customPrincipal, ProfilePatchRequest profilePatchRequest) {
        if (isVerifyExistsEmail(profilePatchRequest.email(), customPrincipal.profileId())) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);
        }

        Profile profile = profileRepository.findById(customPrincipal.profileId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        Optional.ofNullable(profilePatchRequest.name())
                .ifPresent(profile::updateName);
        Optional.ofNullable(profilePatchRequest.email())
                .ifPresent(profile::updateEmail);
        Optional.ofNullable(profilePatchRequest.phoneNumber())
                .ifPresent(profile::updatePhoneNumber);
        Optional.ofNullable(profilePatchRequest.genderType())
                .ifPresent(profile::updateGender);
        Optional.ofNullable(profilePatchRequest.birthDate())
                .ifPresent(profile::updateBirthDate);

        return ProfileDto.from(profile);
    }

    public boolean isVerifyExistsEmail(String email, Long profileId) {
        Optional<Profile> optionalProfile = profileRepository.findByEmail(email);

        return optionalProfile.isPresent() && !Objects.equals(optionalProfile.get().getId(), profileId);
    }
}
