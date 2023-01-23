package com.codestates.culinari.payment.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.payment.dto.PaymentDto;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.dto.request.RefundRequest;
import com.codestates.culinari.payment.dto.response.PaymentFailResponse;
import com.codestates.culinari.payment.dto.response.PaymentSuccessResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentDto createPayment(PaymentRequest dto, CustomPrincipal principal);
    Page<PaymentDto> readPayments(Integer searchMonths, Pageable pageable, CustomPrincipal principal);
    void verifyRequest(String paymentKey, String orderId, BigDecimal amount);
    PaymentSuccessResponse requestApprovalPayment(String paymentKey, String orderId, BigDecimal amount);
    PaymentFailResponse handleRequestFail(String errorCode, String errorMsg, String orderId);
    PaymentSuccessResponse requestPaymentCancel(RefundRequest request, CustomPrincipal principal);
}
