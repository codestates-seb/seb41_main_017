package com.codestates.culinari.user.service.impl;


import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.destination.dto.DestinationDto;
import com.codestates.culinari.destination.entity.Destination;
import com.codestates.culinari.destination.repository.DestinationRepository;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.user.constant.RoleType;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.UserDto;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.dto.request.UserPatchPasswordRequest;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.entitiy.UserRole;
import com.codestates.culinari.user.entitiy.Users;
import com.codestates.culinari.user.repository.ProfileRepository;
import com.codestates.culinari.user.repository.UserRepository;
import com.codestates.culinari.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final DestinationRepository destinationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(SignUpDto signUpDto) {
        verifyExistsUsername(signUpDto.username());
        String encryptedPassword = passwordEncoder.encode(signUpDto.password());

        Profile profile = profileRepository.save(ProfileDto.of(signUpDto).toEntity());
        Destination destination = destinationRepository.save(Destination.of("기본 배송지", signUpDto.address(), signUpDto.name(), signUpDto.phoneNumber(), true, profile));
        Users user = UserDto.of(signUpDto, encryptedPassword, profile).toEntity();
        user.addUserRole(UserRole.of(RoleType.USER, user));

        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    @Override
    public void updatePassword(CustomPrincipal customPrincipal, UserPatchPasswordRequest userPatchPasswordRequest) {
        Users user = userRepository.getReferenceById(customPrincipal.userId());
        String encryptedPassword = passwordEncoder.encode(userPatchPasswordRequest.password());
        user.updatePassword(encryptedPassword);
    }


    @Transactional(readOnly = true)
    @Override
    public void verifyExistsUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new BusinessLogicException(ExceptionCode.USERNAME_EXISTS);
        }
    }
}
