package com.codestates.culinari.customercenter.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.CsInquiryDto;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryResponse;
import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.customercenter.repository.CsInquiryRepository;
import com.codestates.culinari.customercenter.service.CustomerInquiryService;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerInquiryServiceImpl implements CustomerInquiryService {

    private final CsInquiryRepository csInquiryRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void createEnquire(CustomPrincipal customPrincipal, CsInquiryPost csInquiryPost) {
        Profile profile = profileRepository.getReferenceById(customPrincipal.profileId());
        CsInquiryDto csInquiryDto = csInquiryPost.toDto(profile);

        csInquiryRepository.save(csInquiryDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CsInquiryResponse> readInquiriePage(CustomPrincipal customPrincipal, Pageable pageable) {
        return csInquiryRepository.findAllByProfileId(customPrincipal.profileId(), pageable)
                .map(CsInquiryResponse::from);
    }

    @Override
    public void deleteEnquire(CustomPrincipal customPrincipal, Long inquiryId) {
        writtenByMeFindInquiry(customPrincipal,inquiryId);

        csInquiryRepository.deleteById(inquiryId);
    }

    @Override
    public void updateEnquire(CustomPrincipal customPrincipal, Long inquiryId, CsInquiryPatch csInquiryPatch) {
        CsInquiry csInquiry = writtenByMeFindInquiry(customPrincipal, inquiryId);

        Optional.ofNullable(csInquiryPatch.title())
                .ifPresent(csInquiry::updateTitle);
        Optional.ofNullable(csInquiryPatch.content())
                .ifPresent(csInquiry::updateContent);
        Optional.ofNullable(csInquiryPatch.category())
                .ifPresent(csInquiry::updateCategory);
    }

    @Transactional(readOnly = true)
    @Override
    public CsInquiryResponse readInquire(CustomPrincipal customPrincipal, Long inquiryId) {
        CsInquiry csInquiry = writtenByMeFindInquiry(customPrincipal,inquiryId);

        return CsInquiryResponse.from(csInquiry);
    }

    private CsInquiry writtenByMeFindInquiry(CustomPrincipal customPrincipal, Long inquiryId) {
        return csInquiryRepository.findById(inquiryId)
                .filter(a -> verifyAuth(a.getProfile().getId(), customPrincipal.profileId()))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND));
    }

    private boolean verifyAuth(Long targetProfileId, Long profileId) {
        return targetProfileId.equals(profileId);
    }
}
