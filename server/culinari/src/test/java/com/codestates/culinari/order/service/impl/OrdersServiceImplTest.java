package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.repository.CartRepository;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.order.repository.OrdersRepository;
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

import java.time.LocalDateTime;

import static com.codestates.culinari.order.Stub.Stub.createOrderPage;
import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 주문")
@ExtendWith(MockitoExtension.class)
class OrdersServiceImplTest {

    @InjectMocks
    private OrdersServiceImpl sut;
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProfileRepository profileRepository;

//    @DisplayName("주문에 필요한 정보를 입력하면, 주문을 등록한다.")
//    @Test
//    void givenNewOrderInfoAndPrincipal_whenSavingOrder_thenSaveOrder() {
//        // Given
//        OrderRequest orderRequest = createOrderRequest();
//        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
//
//        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
//        given(ordersRepository.save(any(Orders.class))).willReturn(createOrder(1L, 1L));
//        given(cartRepository.findByProfile_IdAndProduct_Id(anyLong(), anyLong())).willReturn(Optional.of(createCart(1L, 1, 1L, 1L)));
//        willDoNothing().given(cartRepository).delete(any(Cart.class));
//        given(orderDetailRepository.save(any(OrderDetail.class))).willReturn(createOrderDetail(1L));
//
//        // When
//        sut.createOrder(orderRequest, principal);
//
//        // Then
//        then(profileRepository).should().getReferenceById(anyLong());
//        then(ordersRepository).should().save(any(Orders.class));
//        verify(cartRepository, times(orderRequest.productIds().size())).findByProfile_IdAndProduct_Id(anyLong(), anyLong());
//        verify(cartRepository, times(orderRequest.productIds().size())).delete(any(Cart.class));
//        verify(orderDetailRepository, times(orderRequest.productIds().size())).save(any(OrderDetail.class));
//    }
//
//    @DisplayName("잘못된 주문 정보를 입력하면, 예외를 반환한다.")
//    @Test
//    void givenNewOrderInfoWithNonexistentCartIdAndPrincipal_whenSavingOrder_thenThrowsException() {
//        // Given
//        OrderRequest orderRequest = createOrderRequest();
//        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
//
//        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
//        given(ordersRepository.save(any(Orders.class))).willReturn(createOrder(1L, 1L));
//        given(cartRepository.findByProfile_IdAndProduct_Id(anyLong(), anyLong())).willReturn(Optional.empty());
//
//        // When
//        Throwable t = catchThrowable(() -> sut.createOrder(orderRequest, principal));
//
//        // Then
//        assertThat(t)
//                .isInstanceOf(BusinessLogicException.class)
//                .hasMessage("Cart not found");
//        then(profileRepository).should().getReferenceById(anyLong());
//        then(ordersRepository).should().save(any(Orders.class));
//        then(cartRepository).should().findByProfile_IdAndProduct_Id(anyLong(), anyLong());
//    }

    @DisplayName("주문을 조회할 기간을 입력하면, 기간내에 주문한 주문 목록을 반환한다.")
    @Test
    void givenSearchMonths_whenSearchingOrders_thenReturnOrderPage() {
        // Given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(ordersRepository.findAllCreatedAfterAndProfile_Id(any(LocalDateTime.class), anyLong(), any(Pageable.class)))
                .willReturn(createOrderPage());

        // When
        sut.readOrders(3, pageable, principal);

        // Then
        then(ordersRepository).should().findAllCreatedAfterAndProfile_Id(any(LocalDateTime.class), anyLong(), any(Pageable.class));
    }
}
