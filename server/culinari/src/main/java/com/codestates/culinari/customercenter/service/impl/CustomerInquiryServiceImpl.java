package com.codestates.culinari.customercenter.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.CsInquiryDto;
import com.codestates.culinari.customercenter.dto.request.CsInquiryRequest;
import com.codestates.culinari.customercenter.dto.response.CsInquiryResponse;
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

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerInquiryServiceImpl implements CustomerInquiryService {

    private final CsInquiryRepository csInquiryRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void createEnquire(CustomPrincipal customPrincipal, CsInquiryRequest csInquiryRequest) {
        Profile profile = profileRepository.getReferenceById(customPrincipal.profileId());
        CsInquiryDto csInquiryDto = csInquiryRequest.toDto(profile);

        csInquiryRepository.save(csInquiryDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CsInquiryResponse> readEnquiriePage(CustomPrincipal customPrincipal, Pageable pageable) {
        Page<CsInquiryDto> csInquiryDtoPage = csInquiryRepository.findAll(pageable)
                .map(CsInquiryDto::from);

        return csInquiryDtoPage.map(CsInquiryResponse::from);
    }

    @Override
    public void deleteEnquire(CustomPrincipal customPrincipal, Long inquiryId) {
        writtenByMeFindInquiry(customPrincipal,inquiryId);

        csInquiryRepository.deleteById(inquiryId);
    }

    @Override
    public void updateEnquire(CustomPrincipal customPrincipal, Long inquiryId, CsInquiryRequest csInquiryRequest) {
        CsInquiryDto csInquiryDto = writtenByMeFindInquiry(customPrincipal,inquiryId);

        csInquiryRepository.save(csInquiryDto.update(csInquiryRequest).toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public CsInquiryResponse readEnquire(CustomPrincipal customPrincipal, Long inquiryId) {
        CsInquiryDto csInquiryDto = writtenByMeFindInquiry(customPrincipal,inquiryId);

        return CsInquiryResponse.from(csInquiryDto);
    }

    private CsInquiryDto writtenByMeFindInquiry(CustomPrincipal customPrincipal, Long inquiryId) {
        return csInquiryRepository.findById(inquiryId)
                .map(CsInquiryDto::from)
                .filter(a -> verifyAuth(a.profile().getId(), customPrincipal.profileId()))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND));
    }

    private boolean verifyAuth(Long targetProfileId, Long profileId) {
        return targetProfileId.equals(profileId);
    }
}
