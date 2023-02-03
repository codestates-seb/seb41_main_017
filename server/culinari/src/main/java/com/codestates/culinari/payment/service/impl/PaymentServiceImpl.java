package com.codestates.culinari.payment.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.dto.OrderDetailDto;
import com.codestates.culinari.order.dto.OrderDto;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.repository.CartRepository;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.order.repository.OrdersRepository;
import com.codestates.culinari.payment.dto.PaymentDto;
import com.codestates.culinari.payment.dto.RefundDto;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.dto.request.RefundRequest;
import com.codestates.culinari.payment.dto.response.PaymentFailResponse;
import com.codestates.culinari.payment.dto.response.PaymentInfoResponse;
import com.codestates.culinari.payment.dto.response.PaymentResponseToPage;
import com.codestates.culinari.payment.dto.response.toss.PaymentTossDto;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.payment.repository.PaymentRepository;
import com.codestates.culinari.payment.repository.RefundRepository;
import com.codestates.culinari.payment.service.PaymentService;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RefundRepository refundRepository;
    private final OrdersRepository ordersRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final ProfileRepository profileRepository;

    @Value("${toss.test.secret-api-key}")
    private String testSecretApiKey;

    @Value("${toss.test.origin-url}")
    private String tossOriginUrl;

    @Override
    public PaymentInfoResponse createPayment(PaymentRequest request, CustomPrincipal principal) {
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        Orders orders = ordersRepository.save(
                OrderDto.of(
                        generateOrderId(),
                        request.address(),
                        request.receiverName(),
                        request.receiverPhoneNumber()
                ).toEntity(profile)
        );

        request.productIds()
                .forEach(productId -> {
                    Cart cart = cartRepository.findByProfile_IdAndProduct_Id(profile.getId(), productId)
                            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CART_NOT_FOUND));

                    OrderDetail orderDetail = orderDetailRepository.save(OrderDetailDto.of(cart.getQuantity()).toEntity(orders, cart.getProduct()));
                    orders.getOrderDetails().add(orderDetail);
                });

        Payment payment = paymentRepository.save(PaymentDto.of(request.payType()).toEntity(orders, profile));
        orders.setPayment(payment);

        return PaymentInfoResponse.from(payment);
    }

    @Override
    public Page<PaymentResponseToPage> readPayments(Integer searchMonths, Pageable pageable, CustomPrincipal principal) {
        return paymentRepository.findAllCreatedAfterAndProfile_Id(LocalDateTime.now().minusMonths(searchMonths), principal.profileId(), pageable)
                .map(PaymentResponseToPage::from);
    }

    @Override
    public void verifyRequest(String paymentKey, String orderId, BigDecimal amount) {
        paymentRepository.findByOrder_id(orderId)
                .ifPresentOrElse(
                        payment ->  {
                            if (payment.getAmount().equals(amount.setScale(2, RoundingMode.HALF_UP))) {
                                payment.setPaymentKey(paymentKey);
                            } else {
                                throw new BusinessLogicException(ExceptionCode.PAYMENT_AMOUNT_NOT_MATCHED);
                            }
                        }, () -> {
                            throw new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND);
                        }
                );
    }

    @Override
    public void requestApprovalPayment(String paymentKey, String orderId, BigDecimal amount) {
        JSONObject params = new JSONObject();
        params.put("amount", amount);
        params.put("orderId", orderId);
        params.put("paymentKey", paymentKey);

        try {
            PaymentTossDto response =
                    new RestTemplate().postForEntity(
                        String.format("%s/payments/confirm", tossOriginUrl),
                        new HttpEntity<>(params, createAuthHeaders()),
                        PaymentTossDto.class
                    ).getBody();
        } catch (Exception e) {
            throw new BusinessLogicException(ExceptionCode.TOSS_REQUEST_FAIL);
        }

        paymentRepository.findByOrder_id(orderId)
                .ifPresentOrElse(
                        payment ->  {
                                payment.setPaySuccessTf(true);
                        }, () -> {
                            throw new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND);
                        }
                );

        cartRepository.deleteAllByOrderId(orderId);
    }

    @Override
    public PaymentFailResponse handleRequestFail(String errorCode, String errorMsg, String orderId) {
        paymentRepository.findByOrder_id(orderId)
                .ifPresentOrElse(
                        payment -> {
                            payment.setPaySuccessTf(false);
                            payment.setPayFailReason(errorMsg);
                        }, () -> { throw new BusinessLogicException(ExceptionCode.PAYMENT_NOT_FOUND); }
                );

        return PaymentFailResponse.of(errorCode, errorMsg, orderId);
    }

    @Override
    public void requestPaymentCancel(RefundRequest request, CustomPrincipal principal) {
        RefundDto dto = request.toDto();

        List<OrderDetail> orderDetails =
                orderDetailRepository.findAllPaidByIdAndProfileId(request.orderDetailIds(), principal.profileId());

        // 들어온 모든 OrderDetailIds 가 같은 결제 키를 가진다는 전제로 흘러감
        // N+1 문제 개선 에서 수정할 예정
        String paymentKey = orderDetailRepository.findPaymentKey(orderDetails.get(0).getId());

        orderDetails.forEach(orderDetail -> {
            JSONObject params = new JSONObject();
            params.put("cancelReason", request.cancelReason());
            params.put("cancelAmount", orderDetail.getPrice());

            try {
                PaymentTossDto paymentTossDto =
                        new RestTemplate().postForEntity(
                                String.format(String.format("%s/payments/%s/cancel", tossOriginUrl, paymentKey)),
                                new HttpEntity<>(params, createAuthHeaders()),
                                PaymentTossDto.class
                        ).getBody();
            } catch (Exception e) {
                throw new BusinessLogicException(ExceptionCode.TOSS_REQUEST_FAIL);
            }

            refundRepository.save(dto.toEntity(orderDetail, paymentKey));
        });
    }

    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String encodedAuth = new String(Base64.getEncoder().encode((testSecretApiKey + ":").getBytes(StandardCharsets.UTF_8)));
        headers.setBasicAuth(encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    private String generateOrderId() {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uniqueKey = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();

        return currentDate + uniqueKey;
    }
}
