package com.codestates.culinari.user.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.response.SingleResponseDto;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.dto.UserDto;
import com.codestates.culinari.user.dto.request.ProfilePatchRequest;
import com.codestates.culinari.user.dto.request.SignUpDto;
import com.codestates.culinari.user.dto.request.UserPatchPasswordRequest;
import com.codestates.culinari.user.dto.response.ProfileMyPageResponseDto;
import com.codestates.culinari.user.service.ProfileService;
import com.codestates.culinari.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity getProfile(@AuthenticationPrincipal CustomPrincipal customPrincipal){
        ProfileMyPageResponseDto profileResponseDto = profileService.readProfile(customPrincipal);

        return new ResponseEntity<>(
                new SingleResponseDto<>(profileResponseDto),
                HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity updateProfile(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                        @Valid @RequestBody ProfilePatchRequest profilePatchRequest){
        ProfileDto profileResponse = profileService.updateProfile(customPrincipal, profilePatchRequest);

        return new ResponseEntity<>(
                new SingleResponseDto<>(profileResponse),
                HttpStatus.OK);
    }

    @PatchMapping("/password-edit")
    public ResponseEntity updatePassword(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                        @Valid @RequestBody UserPatchPasswordRequest userPatchPasswordRequest){
        userService.updatePassword(customPrincipal, userPatchPasswordRequest);

        return new ResponseEntity<>(
                HttpStatus.RESET_CONTENT);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.createUser(signUpDto);

        return new ResponseEntity<>(
                new SingleResponseDto<>(userDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/username-check")
    public ResponseEntity usernameDuplicationCheck(@RequestParam String username) {
        userService.verifyExistsUsername(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/email-check")
    public ResponseEntity emailDuplicationCheck(@RequestParam String email) {
        profileService.verifyExistsEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
