package com.codestates.culinari.customercenter.service.impl;

import com.codestates.culinari.customercenter.dto.CsNoticeDto;
import com.codestates.culinari.customercenter.dto.request.CsNoticePost;
import com.codestates.culinari.customercenter.dto.response.CsNoticeResponse;
import com.codestates.culinari.customercenter.repository.CsNoticeRepository;
import com.codestates.culinari.customercenter.service.CustomerNoticeService;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerNoticeServiceImpl implements CustomerNoticeService {

    private final CsNoticeRepository csNoticeRepository;

    @Override
    public void createNotice(CsNoticePost csNoticePost) {
        CsNoticeDto csNoticeDto = csNoticePost.toDto();

        csNoticeRepository.save(csNoticeDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CsNoticeResponse> readNoticePage(Pageable pageable) {
        Page<CsNoticeDto> csNoticeDtoPage = csNoticeRepository.findAll(pageable)
                .map(CsNoticeDto::from);

        return csNoticeDtoPage.map(CsNoticeResponse::from);
    }

    @Transactional(readOnly = true)
    @Override
    public CsNoticeResponse readNotice(Long noticeId) {
        return csNoticeRepository.findById(noticeId)
                .map(CsNoticeDto::from)
                .map(CsNoticeResponse::from)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOTICE_NOT_FOUND));
    }
}
