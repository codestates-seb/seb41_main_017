package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.dto.OrderDetailDto;
import com.codestates.culinari.order.dto.OrderDto;
import com.codestates.culinari.order.dto.request.OrderRequest;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.entitiy.Orders;
import com.codestates.culinari.order.repository.CartRepository;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.order.repository.OrdersRepository;
import com.codestates.culinari.order.service.OrdersService;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
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
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final ProfileRepository profileRepository;

    @Override   // TODO: 결제와 동시에 생성됨을 PR에 명시하고 FE에 알릴 것
    public void createOrder(OrderRequest request, CustomPrincipal principal) {
        verifyPrincipal(principal);

        Profile profile = profileRepository.getReferenceById(principal.profileId());
        OrderDto dto = request.toDto();
        Orders orders = ordersRepository.save(dto.toEntity(profile));

        request.productIds()
                .forEach(productId -> {
                    Cart cart = cartRepository.findByProfile_IdAndProduct_Id(profile.getId(), productId)
                                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CART_NOT_FOUND));
                    cartRepository.delete(cart);
                    OrderDetailDto.from(orderDetailRepository.save(OrderDetailDto.of(cart.getQuantity()).toEntity(orders, cart.getProduct())));
                });
    }

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
