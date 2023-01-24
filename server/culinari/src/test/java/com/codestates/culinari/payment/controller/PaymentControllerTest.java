package com.codestates.culinari.payment.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.dto.request.RefundRequest;
import com.codestates.culinari.payment.dto.response.PaymentResponseToPage;
import com.codestates.culinari.payment.service.PaymentService;
import config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static com.codestates.culinari.order.Stub.Stub.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 결제")
@Import(TestSecurityConfig.class)
@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    private final MockMvc mvc;

    @MockBean
    private PaymentService paymentService;
    @MockBean
    private PaginationService paginationService;

    public PaymentControllerTest(@Autowired MockMvc mvc) { this.mvc = mvc; }

    @DisplayName("[POST] 결제(주문, 결제) 등록 - 정상호출")
    @Test
    void givenNewPaymentInfo_whenRequesting_thenSavesNewPaymentAndOrder() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
        String requestBody = """
                {
                    "payType": "CARD",
                    "productIds": [1, 2, 3],
                    "address": "배송 주소 명",
                    "receiverName": "수령자 명",
                    "receiverPhoneNumber": "010-0000-0000"
                }
                """;

        given(paymentService.createPayment(any(PaymentRequest.class), any(CustomPrincipal.class))).willReturn(createPaymentInfoResponse());

        // When & Then
        mvc.perform(post("/payments")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isCreated());

        then(paymentService).should().createPayment(any(PaymentRequest.class), any(CustomPrincipal.class));
    }

    @DisplayName("[GET] 결제 목록 조회 - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenReturnPaymentPage() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);

        Page<PaymentResponseToPage> paymentPage = createPaymentPage().map(PaymentResponseToPage::from);

        given(paymentService.readPayments(anyInt(), any(Pageable.class), any(CustomPrincipal.class))).willReturn(paymentPage);
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0,1,2));

        // When & Then
        mvc.perform(get("/payments")
                        .with(authentication(auth))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        then(paymentService).should().readPayments(anyInt(), any(Pageable.class), any(CustomPrincipal.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[GET] 토스 결제 승인 (토스 결제 생성 성공 시에 redirect) - 정상호출")
    @Test
    void givenPaymentInfo_whenRequestingApprovingPayment_thenReturnPaymentInfo() throws Exception {
        willDoNothing().given(paymentService).verifyRequest(anyString(), anyString(), any(BigDecimal.class));
        given(paymentService.requestApprovalPayment(anyString(), anyString(), any(BigDecimal.class))).willReturn(createPaymentTossDto());

        // When & Then
        mvc.perform(get("/payments/success")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("paymentKey", "paymentKey")
                        .param("orderId", "0".repeat(18) + "1")
                        .param("amount", "1000")
                )
                .andExpect(status().isOk());

        then(paymentService).should().verifyRequest(anyString(), anyString(), any(BigDecimal.class));
        then(paymentService).should().requestApprovalPayment(anyString(), anyString(), any(BigDecimal.class));
    }

    @DisplayName("[GET] 토스 결제 생성 실패 (토스 결제 생성 실패 시에 redirect) - 정상호출")
    @Test
    void givenErrorInfo_whenFailToCreatingPayment_thenReturnErrorInfo() throws Exception {
        given(paymentService.handleRequestFail(anyString(), anyString(), anyString())).willReturn(createPaymentFailResponse());

        // When & Then
        mvc.perform(get("/payments/fail")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("code", "CODE")
                        .param("message", "에러 메세지")
                        .param("orderId", "0".repeat(18) + "1")
                )
                .andExpect(status().isOk());

        then(paymentService).should().handleRequestFail(anyString(), anyString(), anyString());
    }

    @DisplayName("[POST] 토스 결제 취소(환불) 요청 - 정상호출")
    @Test
    void givenPaymentInfo_whenRequestingCancelPayment_thenReturnPaymentInfo() throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
        String requestBody = """
                {
                    "orderDetailId": 1,
                    "paymentKey": "paymentKey",
                    "cancelReason": "환불 사유"
                }
                """;

        given(paymentService.requestPaymentCancel(any(RefundRequest.class), any(CustomPrincipal.class))).willReturn(createPaymentTossDto());

        // When & Then
        mvc.perform(post("/payments/cancel")
                        .with(authentication(auth))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk());

        then(paymentService).should().requestPaymentCancel(any(RefundRequest.class), any(CustomPrincipal.class));
    }
}
