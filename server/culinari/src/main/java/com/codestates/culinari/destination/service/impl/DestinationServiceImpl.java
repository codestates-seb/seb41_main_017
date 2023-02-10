package com.codestates.culinari.destination.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.destination.dto.DestinationDto;
import com.codestates.culinari.destination.dto.request.DestinationPatch;
import com.codestates.culinari.destination.dto.request.DestinationPost;
import com.codestates.culinari.destination.dto.response.DestinationResponse;
import com.codestates.culinari.destination.entity.Destination;
import com.codestates.culinari.destination.repository.DestinationRepository;
import com.codestates.culinari.destination.service.DestinationService;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final ProfileRepository profileRepository;

    @Override
    public DestinationResponse createDestination(CustomPrincipal customPrincipal, DestinationPost destinationPost) {
        Profile profile = profileRepository.getReferenceById(customPrincipal.profileId());
        DestinationDto destinationDto = destinationPost.toDto();

        Destination destination = destinationRepository.save(destinationDto.toEntity(profile));
        if (destination.isDefaultSelect()) {
            updateDestinationDefaultState(customPrincipal, destination.getId());
        }

        return DestinationResponse.from(DestinationDto.from(destination));
    }

    @Override
    public void deleteDestination(CustomPrincipal customPrincipal, Long destinationId) {
        Destination destination = destinationRepository.findById(destinationId)
                .filter(d -> Objects.equals(d.getProfile().getId(), customPrincipal.profileId()))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DESTINATION_NOT_FOUND));

        destinationRepository.delete(destination);
    }

    @Override
    public void updateDestination(CustomPrincipal customPrincipal, Long destinationId, DestinationPatch destinationPatch) {
        Destination destination = destinationRepository.findById(destinationId)
                .filter(d -> Objects.equals(d.getProfile().getId(), customPrincipal.profileId()))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DESTINATION_NOT_FOUND));

        destination.updateDestination(destinationPatch);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DestinationResponse> readDestinationList(CustomPrincipal customPrincipal) {
        return destinationRepository.findAllByProfileId(customPrincipal.profileId()).stream()
                .map(DestinationDto::from)
                .map(DestinationResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public DestinationResponse readDestination(CustomPrincipal customPrincipal, Long destinationId) {
        return destinationRepository.findById(destinationId)
                .filter(d -> Objects.equals(d.getProfile().getId(), customPrincipal.profileId()))
                .map(DestinationDto::from)
                .map(DestinationResponse::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DESTINATION_NOT_FOUND));
    }

    // 백엔드 개발자 대표 주소 가져올때 사용. (필요한가??)
    @Override
    public DestinationResponse getDestinationDefaultState(CustomPrincipal customPrincipal) {
        return destinationRepository.findAllByProfileId(customPrincipal.profileId()).stream()
                .filter(d -> Objects.equals(d.getDefaultSelect(), true))
                .findFirst()
                .map(DestinationDto::from)
                .map(DestinationResponse::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.DESTINATION_NOT_FOUND));
    }

    @Override
    public void updateDestinationDefaultState(CustomPrincipal customPrincipal, Long destinationId) {
        destinationRepository.findAllByProfileId(customPrincipal.profileId()).stream()
                .filter(d -> Objects.equals(d.getDefaultSelect(), true))
                .findFirst()
                .ifPresent(Destination::defaultSelectToFalse);

        destinationRepository.findById(destinationId)
                .ifPresent(Destination::defaultSelectToTrue);
    }
}
