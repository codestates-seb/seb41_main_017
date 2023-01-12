package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryRequest;
import com.codestates.culinari.customercenter.dto.response.CsInquiryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerInquiryService {
    void createEnquire(CustomPrincipal customPrincipal, CsInquiryRequest csInquiryPost);

    Page<CsInquiryResponse> readEnquiriePage(CustomPrincipal customPrincipal, Pageable pageable);

    void deleteEnquire(CustomPrincipal customPrincipal, Long inquiryId);

    void updateEnquire(CustomPrincipal customPrincipal, Long inquiryId, CsInquiryRequest csInquiryRequest);

    CsInquiryResponse readEnquire(CustomPrincipal customPrincipal, Long inquiryId);
}
