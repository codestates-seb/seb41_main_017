package com.codestates.culinari.order.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.response.OrderResponse;
import com.codestates.culinari.order.service.OrdersService;
import com.codestates.culinari.pagination.service.PaginationService;
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

import java.util.List;

import static com.codestates.culinari.order.Stub.Stub.createOrderPage;
import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 장바구니")
@Import(TestSecurityConfig.class)
@WebMvcTest(OrdersController.class)
class OrdersControllerTest {

    private final MockMvc mvc;

    @MockBean
    private OrdersService ordersService;
    @MockBean
    private PaginationService paginationService;

    public OrdersControllerTest(@Autowired MockMvc mvc) { this.mvc = mvc; }

    @DisplayName("[GET] 장바구니 목록 조회 - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenReturnOrderPage() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);

        Page<OrderResponse> orderPage = createOrderPage().map(OrderResponse::from);

        given(ordersService.readOrders(anyInt(), any(Pageable.class), any(CustomPrincipal.class))).willReturn(orderPage);
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0,1,2));

        // When & Then
        mvc.perform(get("/orders")
                        .with(authentication(auth))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        then(ordersService).should().readOrders(anyInt(), any(Pageable.class), any(CustomPrincipal.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }
}
