package com.codestates.culinari.order.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import com.codestates.culinari.order.dto.response.CartResponse;
import com.codestates.culinari.order.service.CartService;
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

import static com.codestates.culinari.order.Stub.Stub.createCartPage;
import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 - 장바구니")
@Import(TestSecurityConfig.class)
@WebMvcTest(CartController.class)
class CartControllerTest {

    private final MockMvc mvc;

    @MockBean
    private CartService cartService;
    @MockBean
    private PaginationService paginationService;

    public CartControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[POST] 장바구니 등록 - 정상호출")
    @Test
    void givenNewCartInfo_whenRequesting_thenSavesNewCart() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
        String requestBody = """
                {
                    "productId": 1,
                    "quantity": 3
                }
                """;

        willDoNothing().given(cartService).createCart(any(CartPost.class), any(CustomPrincipal.class));

        // When & Then
        mvc.perform(post("/carts")
                .with(authentication(auth))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(status().isCreated());

        then(cartService).should().createCart(any(CartPost.class), any(CustomPrincipal.class));
    }

    @DisplayName("[GET] 장바구니 목록 조회 - 정상호출")
    @Test
    void givenNothing_whenRequesting_thenReturnCartPage() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);

        Page<CartResponse> cartPage = createCartPage().map(CartResponse::from);

        given(cartService.readCarts(any(Pageable.class), any(CustomPrincipal.class))).willReturn(cartPage);
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0,1,2));

        // When & Then
        mvc.perform(get("/carts")
                        .with(authentication(auth))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        then(cartService).should().readCarts(any(Pageable.class), any(CustomPrincipal.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[PATCH] 장바구니 수정 - 정상호출")
    @Test
    void givenCartIdAndModifiedCartInfo_whenRequesting_thenUpdatingCart() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
        Long cartId = 1L;
        String requestBody = """
                {
                    "quantity": 3
                }
                """;

        willDoNothing().given(cartService).updateCart(any(CartPatch.class), anyLong(), any(CustomPrincipal.class));

        // When & Then
        mvc.perform(patch("/carts/{cartId}", cartId)
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk());

        then(cartService).should().updateCart(any(CartPatch.class), anyLong(), any(CustomPrincipal.class));
    }

    @DisplayName("[DELETE] 장바구니 삭제 - 정상호출")
    @Test
    void givenCartId_whenRequesting_thenDeletingCart() throws Exception {
        // Given
        Authentication auth = new UsernamePasswordAuthenticationToken(createPrincipal("사용자 명", 1L, 1L), null);
        Long cartId = 1L;

        willDoNothing().given(cartService).deleteCart(anyLong(), any(CustomPrincipal.class));

        // When & Then
        mvc.perform(delete("/carts/{cartId}", cartId)
                        .with(authentication(auth))
                )
                .andExpect(status().isNoContent());

        then(cartService).should().deleteCart(anyLong(), any(CustomPrincipal.class));
    }
}
