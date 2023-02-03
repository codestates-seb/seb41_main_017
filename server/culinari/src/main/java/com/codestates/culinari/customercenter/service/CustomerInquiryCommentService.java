package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryCommentPatch;
import com.codestates.culinari.customercenter.dto.request.CsInquiryCommentPost;
import com.codestates.culinari.customercenter.dto.response.CsInquiryCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerInquiryCommentService {
    CsInquiryCommentResponse createEnquireComment(CustomPrincipal customPrincipal, CsInquiryCommentPost csInquiryCommentPost, Long inquiryId);

    Page<CsInquiryCommentResponse> readEnquirieCommentPage(CustomPrincipal customPrincipal, Pageable pageable, Long inquiryId);

    CsInquiryCommentResponse readEnquireComment(CustomPrincipal customPrincipal, Long commentId);

    void deleteInquireComment(CustomPrincipal customPrincipal, Long commentId);

    void updateEnquireComment(CustomPrincipal customPrincipal, Long commentId, CsInquiryCommentPatch csInquiryCommentPatch);
}
