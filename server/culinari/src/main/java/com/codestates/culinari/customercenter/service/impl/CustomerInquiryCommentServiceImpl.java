package com.codestates.culinari.customercenter.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.CsInquiryCommentDto;
import com.codestates.culinari.customercenter.dto.request.CsInquiryCommentPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryCommentPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryCommentResponse;
import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.customercenter.entity.CsInquiryComment;
import com.codestates.culinari.customercenter.repository.CsInquiryCommentRepository;
import com.codestates.culinari.customercenter.repository.CsInquiryRepository;
import com.codestates.culinari.customercenter.service.CustomerInquiryCommentService;
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
public class CustomerInquiryCommentServiceImpl implements CustomerInquiryCommentService {

    private final CsInquiryCommentRepository csInquiryCommentRepository;
    private final CsInquiryRepository csInquiryRepository;
    private final ProfileRepository profileRepository;

    @Override
    public CsInquiryCommentResponse createEnquireComment(CustomPrincipal customPrincipal, CsInquiryCommentPost csInquiryCommentPost, Long inquiryId) {
        CsInquiry csInquiry = csInquiryRepository.getReferenceById(inquiryId);
        Profile profile = profileRepository.getReferenceById(customPrincipal.profileId());
        CsInquiryCommentDto csInquiryCommentDto = csInquiryCommentPost.toDto(csInquiry, profile);

        CsInquiryComment csInquiryComment = csInquiryCommentRepository.save(csInquiryCommentDto.toEntity());

        return CsInquiryCommentResponse.from(csInquiryComment);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CsInquiryCommentResponse> readEnquirieCommentPage(CustomPrincipal customPrincipal, Pageable pageable, Long inquiryId) {
        return csInquiryCommentRepository.findAllByInquiryId(pageable, inquiryId)
                .map(CsInquiryCommentResponse::from);
    }

    @Transactional(readOnly = true)
    @Override
    public CsInquiryCommentResponse readEnquireComment(CustomPrincipal customPrincipal, Long commentId) {
        CsInquiryComment csInquiryComment = writtenByMeFindInquiryComment(customPrincipal,commentId);

        return CsInquiryCommentResponse.from(csInquiryComment);
    }

    @Override
    public void deleteInquireComment(CustomPrincipal customPrincipal, Long commentId) {
        //TODO 메소드 정상 작동 안함
        CsInquiryComment csInquiryComment = writtenByMeFindInquiryComment(customPrincipal, commentId);

        csInquiryCommentRepository.delete(csInquiryComment);
    }

    @Override
    public void updateEnquireComment(CustomPrincipal customPrincipal, Long commentId, CsInquiryCommentPatch csInquiryCommentPatch) {
        CsInquiryComment csInquiryComment = writtenByMeFindInquiryComment(customPrincipal, commentId);

        Optional.ofNullable(csInquiryCommentPatch.content())
                .ifPresent(csInquiryComment::updateContent);
    }

    public CsInquiryComment writtenByMeFindInquiryComment(CustomPrincipal customPrincipal, Long commentId) {
        return csInquiryCommentRepository.findByIdAndProfileId(commentId, customPrincipal.profileId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INQUIRYCOMMENT_NOT_FOUND));
    }
}
