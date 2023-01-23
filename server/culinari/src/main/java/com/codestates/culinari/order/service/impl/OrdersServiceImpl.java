package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.dto.OrderDto;
import com.codestates.culinari.order.repository.OrdersRepository;
import com.codestates.culinari.order.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;


    @Override
    public Page<OrderDto> readOrders(Integer searchMonths, Pageable pageable, CustomPrincipal principal) {
        verifyPrincipal(principal);

        return ordersRepository.findAllCreatedAfterAndProfile_Id(LocalDateTime.now().minusMonths(searchMonths), principal.profileId(), pageable)
                .map(OrderDto::from);
    }

    public void verifyPrincipal(CustomPrincipal principal) {
        if (principal == null) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }
}
