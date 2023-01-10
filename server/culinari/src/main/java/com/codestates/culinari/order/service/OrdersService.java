package com.codestates.culinari.order.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.OrderDto;
import com.codestates.culinari.order.dto.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrdersService {
    void createOrder(OrderRequest dto, CustomPrincipal principal);

    Page<OrderDto> readOrders(Integer searchMonths, Pageable pageable, CustomPrincipal principal);
}
