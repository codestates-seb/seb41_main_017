package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.repository.OrdersRepository;
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
