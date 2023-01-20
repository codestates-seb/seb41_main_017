package com.codestates.culinari.order.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.OrderDto;
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

//    @DisplayName("[POST] 주문 등록 - 정상호출")
//    @Test
//    void givenNewOrderInfo_whenRequesting_thenSavesNewOrder() throws Exception {
//        // Given
//        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
//        String requestBody = """
//                {
//                    "productIds": [1, 2, 3],
//                    "address": "배송 주소 명",
//                    "receiverName": "수령자 명",
//                    "receiverPhoneNumber": "010-0000-0000"
//                }
//                """;
//
//        willDoNothing().given(ordersService).createOrder(any(OrderRequest.class), any(CustomPrincipal.class));
//
//        // When & Then
//        mvc.perform(post("/orders")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody)
//                )
//                .andExpect(status().isCreated());
//
//        then(ordersService).should().createOrder(any(OrderRequest.class), any(CustomPrincipal.class));
//    }

    @DisplayName("[GET] 장바구니 목록 조회 - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenReturnOrderPage() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);

        Page<OrderDto> orderDtoPage = createOrderPage().map(OrderDto::from);

        given(ordersService.readOrders(anyInt(), any(Pageable.class), any(CustomPrincipal.class))).willReturn(orderDtoPage);
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
