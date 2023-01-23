package com.codestates.culinari.payment.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.repository.CartRepository;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.order.repository.OrdersRepository;
import com.codestates.culinari.payment.constant.PayType;
import com.codestates.culinari.payment.dto.request.PaymentRequest;
import com.codestates.culinari.payment.entity.Payment;
import com.codestates.culinari.payment.repository.PaymentRepository;
import com.codestates.culinari.payment.repository.RefundRepository;
import com.codestates.culinari.user.repository.ProfileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.codestates.culinari.order.Stub.Stub.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 결제")
@TestPropertySource(locations = "classpath:application.yaml")
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl sut;
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RefundRepository refundRepository;
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProfileRepository profileRepository;

    @DisplayName("결제할 상품 정보를 입력하면, 주문과 결제를 생성한다.")
    @Test
    void givenNewPaymentInfoAndPrincipal_whenSavingPaymentAndOrder_thenSavePaymentAndOrder() {
        // Given
        PaymentRequest paymentRequest = createPaymentRequest();
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
        given(ordersRepository.save(any(Orders.class))).willReturn(createOrder(1L, 1L));
        given(cartRepository.findByProfile_IdAndProduct_Id(anyLong(), anyLong())).willReturn(Optional.of(createCart(1L, 1, 1L, 1L)));
        willDoNothing().given(cartRepository).delete(any(Cart.class));
        given(orderDetailRepository.save(any(OrderDetail.class))).willReturn(createOrderDetail(1L));
        given(paymentRepository.save(any(Payment.class))).willReturn(createPayment(PayType.CARD, 1000, "상품명 외 2개"));

        // When
        sut.createPayment(paymentRequest, principal);

        // Then
        then(profileRepository).should().getReferenceById(anyLong());
        then(ordersRepository).should().save(any(Orders.class));
        then(cartRepository).should(times(paymentRequest.productIds().size())).findByProfile_IdAndProduct_Id(anyLong(), anyLong());
        then(cartRepository).should(times(paymentRequest.productIds().size())).delete(any(Cart.class));
        then(orderDetailRepository).should(times(paymentRequest.productIds().size())).save(any(OrderDetail.class));
        then(paymentRepository).should().save(any(Payment.class));

    }

    @DisplayName("잘못된 결제 정보를 입력하면, 예외를 반환한다.")
    @Test
    void givenNewPaymentInfoWithNonexistentCartIdAndPrincipal_whenSavingPaymentAndOrder_thenThrowsException() {
        // Given
        PaymentRequest paymentRequest = createPaymentRequest();
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
        given(ordersRepository.save(any(Orders.class))).willReturn(createOrder(1L, 1L));
        given(cartRepository.findByProfile_IdAndProduct_Id(anyLong(), anyLong())).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.createPayment(paymentRequest, principal));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Cart not found");
        then(profileRepository).should().getReferenceById(anyLong());
        then(ordersRepository).should().save(any(Orders.class));
        then(cartRepository).should().findByProfile_IdAndProduct_Id(anyLong(), anyLong());
    }

    @DisplayName("결제를 조회할 기간을 입력하면, 기간내에 존재하는 결제 목록을 반환한다.")
    @Test
    void givenSearchMonths_whenSearchingOrders_thenReturnOrderPage() {
        // Given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(paymentRepository.findAllCreatedAfterAndProfile_Id(any(LocalDateTime.class), anyLong(), any(Pageable.class)))
                .willReturn(createPaymentPage());

        // When
        sut.readPayments(3, pageable, principal);

        // Then
        then(paymentRepository).should().findAllCreatedAfterAndProfile_Id(any(LocalDateTime.class), anyLong(), any(Pageable.class));
    }

    @DisplayName("결제 정보가 반환 되면, DB에 존재하는지 확인한다.")
    @Test
    void givenPaymentInfoFromToss_whenVerifyingPayment_thenVerifyPayment() {
        // Given
        given(paymentRepository.findByOrder_id(anyLong())).willReturn(Optional.of(createPayment(PayType.CARD, 1000, "상품명")));

        // When
        sut.verifyRequest("paymentKey", "0".repeat(18) +"1", BigDecimal.valueOf(1000));

        // Then
        then(paymentRepository).should().findByOrder_id(anyLong());
    }

    @DisplayName("잘못된 금액의 결제 정보가 반환 되면, 예외를 반환한다.")
    @Test
    void givenPaymentInfoWithWrongAmountFromToss_whenVerifyingPayment_thenThrowsException() {
        // Given
        given(paymentRepository.findByOrder_id(anyLong())).willReturn(Optional.of(createPayment(PayType.CARD, 1000, "상품명")));

        // When
        Throwable t = catchThrowable(() -> sut.verifyRequest("paymentKey", "0".repeat(18) +"1", BigDecimal.valueOf(1001)));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Payment amount not matched");
        then(paymentRepository).should().findByOrder_id(anyLong());
    }

    @DisplayName("잘못된 주문의 결제 정보가 반환 되면, 예외를 반환한다.")
    @Test
    void givenPaymentInfoWithWrongOrderIdFromToss_whenVerifyingPayment_thenThrowsException() {
        // Given
        given(paymentRepository.findByOrder_id(anyLong())).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.verifyRequest("paymentKey", "0".repeat(18) +"1", BigDecimal.valueOf(1001)));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Payment not found");
        then(paymentRepository).should().findByOrder_id(anyLong());
    }

//    @Test
//    void requestApprovalPayment() {
//    }

    @Test
    void givenErrorInfoWithOrderId_whenFailApprovalPayment_thenUpdatesPayment() {
        // Given
        given(paymentRepository.findByOrder_id(anyLong())).willReturn(Optional.of(createPayment(PayType.CARD, 1000, "상품명")));

        // When
        sut.handleRequestFail("NOT_FOUND_PAYMENT", "존재하지 않는 결제 입니다.", "0".repeat(18) +"1");

        // Then
        then(paymentRepository).should().findByOrder_id(anyLong());
    }

    @Test
    void givenErrorInfoWithWrongOrderId_whenFailApprovalPayment_thenThrowsException() {
        // Given
        given(paymentRepository.findByOrder_id(anyLong())).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.handleRequestFail("NOT_FOUND_PAYMENT", "존재하지 않는 결제 입니다.", "0".repeat(18) +"1"));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Payment not found");
        then(paymentRepository).should().findByOrder_id(anyLong());
    }

//    @Test
//    void requestPaymentCancel() {
//    }
}
