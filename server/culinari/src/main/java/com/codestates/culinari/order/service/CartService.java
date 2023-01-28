package com.codestates.culinari.order.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.request.CartDelete;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import com.codestates.culinari.order.dto.response.CartResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {

    void createCart(CartPost post, CustomPrincipal principal);

    Page<CartResponse> readCarts(Pageable pageable, CustomPrincipal principal);

    void updateCart(CartPatch patch, Long cartId, CustomPrincipal principal);

    void deleteCarts(CartDelete delete, CustomPrincipal principal);
}
