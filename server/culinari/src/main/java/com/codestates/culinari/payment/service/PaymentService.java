package com.codestates.culinari.payment.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.dto.request.RefundRequest;
import com.codestates.culinari.payment.dto.response.PaymentFailResponse;
import com.codestates.culinari.payment.dto.response.PaymentInfoResponse;
import com.codestates.culinari.payment.dto.response.PaymentResponseToPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentInfoResponse createPayment(PaymentRequest dto, CustomPrincipal principal);
    Page<PaymentResponseToPage> readPayments(Integer searchMonths, Pageable pageable, CustomPrincipal principal);
    void verifyRequest(String paymentKey, String orderId, BigDecimal amount);
    void requestApprovalPayment(String paymentKey, String orderId, BigDecimal amount);
    PaymentFailResponse handleRequestFail(String errorCode, String errorMsg, String orderId);
    void requestPaymentCancel(RefundRequest request, CustomPrincipal principal);
}
