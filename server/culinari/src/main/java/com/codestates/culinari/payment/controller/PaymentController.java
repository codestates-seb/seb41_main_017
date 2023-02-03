package com.codestates.culinari.payment.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.dto.request.RefundRequest;
import com.codestates.culinari.payment.dto.response.PaymentFailResponse;
import com.codestates.culinari.payment.dto.response.PaymentInfoResponse;
import com.codestates.culinari.payment.dto.response.PaymentResponseToPage;
import com.codestates.culinari.payment.service.PaymentService;
import com.codestates.culinari.response.SingleResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaginationService paginationService;

    @PostMapping
    public ResponseEntity postPayment(
            @Valid @RequestBody PaymentRequest request,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        PaymentInfoResponse paymentInfo = paymentService.createPayment(request, principal);

        return new ResponseEntity<>(
                new SingleResponseDto<>(paymentInfo),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity getPayments(
            @Min(0) @RequestParam(defaultValue = "0", required = false) int page,
            @Positive @RequestParam(defaultValue = "10", required = false) int size,
            @Positive @RequestParam(defaultValue = "3") Integer searchMonths,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<PaymentResponseToPage> pagePayments = paymentService.readPayments(searchMonths, pageable, principal);
        List<PaymentResponseToPage> payments = pagePayments.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, pagePayments.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(payments, pagePayments, barNumber),
                HttpStatus.OK
        );
    }

    @GetMapping("/success")
    public ResponseEntity requestPaymentSuccess(
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam BigDecimal amount
    ) {
        paymentService.verifyRequest(paymentKey, orderId, amount);
        //PaymentTossDto response =
        paymentService.requestApprovalPayment(paymentKey, orderId, amount);

        return new ResponseEntity<>(
                //new SingleResponseDto<>(response),
                HttpStatus.OK
        );
    }

    @GetMapping("/fail")
    public ResponseEntity requestPaymentFail(
        @RequestParam(name = "code") String errorCode,
        @RequestParam(name = "message") String errorMsg,
        @RequestParam String orderId
    ) {
        PaymentFailResponse paymentFailResponse = paymentService.handleRequestFail(errorCode, errorMsg, orderId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(paymentFailResponse),
                HttpStatus.OK
        );
    }

    @PostMapping("/cancel")
    public ResponseEntity requestPaymentCancel(
            @Valid @RequestBody RefundRequest request,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
            //PaymentTossDto response =
            paymentService.requestPaymentCancel(request, principal);

            return new ResponseEntity<>(
                    //new SingleResponseDto<>(response),
                    HttpStatus.OK
            );

    }
}
