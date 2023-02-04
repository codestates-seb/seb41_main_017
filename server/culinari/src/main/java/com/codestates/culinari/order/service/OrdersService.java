package com.codestates.culinari.order.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.response.OrderDetailResponse;
import com.codestates.culinari.order.dto.response.OrderResponse;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewEnableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrdersService {
    Page<ProfileMyPageReviewEnableResponse> readEnableReviewOrder(CustomPrincipal principal, Pageable pageable);

    Page<OrderResponse> readOrders(Integer searchMonths, Pageable pageable, CustomPrincipal principal);

    Page<OrderDetailResponse> readOrderDetails(Integer searchMonths, Pageable pageable, CustomPrincipal principal);
}
