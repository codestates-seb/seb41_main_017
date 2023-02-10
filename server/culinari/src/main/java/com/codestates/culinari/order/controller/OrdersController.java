package com.codestates.culinari.order.controller;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.dto.response.OrderDetailResponse;
import com.codestates.culinari.order.dto.response.OrderResponse;
import com.codestates.culinari.order.service.OrdersService;
import com.codestates.culinari.pagination.PageResponseDto;
import com.codestates.culinari.pagination.service.PaginationService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final PaginationService paginationService;

    @GetMapping
    public ResponseEntity getOrders(
            @Min(0) @RequestParam(defaultValue = "0", required = false) int page,
            @Positive @RequestParam(defaultValue = "10", required = false) int size,
            @Positive @RequestParam(defaultValue = "3") Integer searchMonths,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<OrderResponse> pageOrders = ordersService.readOrders(searchMonths, pageable, principal);
        List<OrderResponse> orders = pageOrders.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, pageOrders.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(orders, pageOrders, barNumber),
                HttpStatus.OK
        );
    }

    @GetMapping("/details")
    public ResponseEntity getOrderDetails(
            @Min(0) @RequestParam(defaultValue = "0", required = false) int page,
            @Positive @RequestParam(defaultValue = "10", required = false) int size,
            @Positive @RequestParam(defaultValue = "3") Integer searchMonths,
            @AuthenticationPrincipal CustomPrincipal principal
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<OrderDetailResponse> pageOrderDetails = ordersService.readOrderDetails(searchMonths, pageable, principal);
        List<OrderDetailResponse> orderDetails = pageOrderDetails.getContent();
        List<Integer> barNumber = paginationService.getPaginationBarNumbers(page, pageOrderDetails.getTotalPages());

        return new ResponseEntity<>(
                new PageResponseDto<>(orderDetails, pageOrderDetails, barNumber),
                HttpStatus.OK
        );
    }
}
