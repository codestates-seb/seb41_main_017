package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.dto.response.OrderDetailResponse;
import com.codestates.culinari.order.dto.response.OrderResponse;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.order.repository.OrdersRepository;
import com.codestates.culinari.order.service.OrdersService;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewEnableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public Page<ProfileMyPageReviewEnableResponse> readEnableReviewOrder(CustomPrincipal principal, Pageable pageable) {
        return orderDetailRepository.findAllByOrdersProfileIdAndProductReviewIsNull
                (principal.profileId(), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending())).map(ProfileMyPageReviewEnableResponse::from);
    }


    @Override
    public Page<OrderResponse> readOrders(Integer searchMonths, Pageable pageable, CustomPrincipal principal) {
        return ordersRepository.findAllCreatedAfterAndProfile_Id(LocalDateTime.now().minusMonths(searchMonths), principal.profileId(), pageable)
                .map(OrderResponse::from);
    }

    @Override
    public Page<OrderDetailResponse> readOrderDetails(Integer searchMonths, Pageable pageable, CustomPrincipal principal) {
        return orderDetailRepository.findAllCreatedAfterAndProfile_Id(LocalDateTime.now().minusMonths(searchMonths), principal.profileId(), pageable)
                .map(OrderDetailResponse::from);
    }
}
