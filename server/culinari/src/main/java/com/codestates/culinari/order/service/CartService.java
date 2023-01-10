package com.codestates.culinari.order.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.CartDto;
import com.codestates.culinari.order.dto.OrderDto;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {

    void createCart(CartPost post, CustomPrincipal principal);

    Page<CartDto> readCarts(Pageable pageable, CustomPrincipal principal);

    void updateCart(CartPatch patch, Long cartId, CustomPrincipal principal);

    void deleteCart(Long cartId, CustomPrincipal principal);
}
