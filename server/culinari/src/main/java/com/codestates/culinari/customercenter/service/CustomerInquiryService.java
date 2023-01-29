package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerInquiryService {
    void createEnquire(CustomPrincipal customPrincipal, CsInquiryPost csInquiryPost);

    Page<CsInquiryResponse> readInquiriePage(CustomPrincipal customPrincipal, Pageable pageable);

    void deleteEnquire(CustomPrincipal customPrincipal, Long inquiryId);

    void updateEnquire(CustomPrincipal customPrincipal, Long inquiryId, CsInquiryPatch csInquiryPatch);

    CsInquiryResponse readInquire(CustomPrincipal customPrincipal, Long inquiryId);
}
