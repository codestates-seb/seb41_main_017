package com.codestates.culinari.user.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.order.repository.OrdersRepository;
import com.codestates.culinari.product.repository.ProductLikeRepository;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.user.dto.response.MyPageCountResponse;
import com.codestates.culinari.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class MyPageServiceImpl implements MyPageService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrdersRepository ordersRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ProductRepository productRepository;

    @Override
    public MyPageCountResponse getMyPageCountData(CustomPrincipal principal) {
        Long shippingCount = orderDetailRepository.countOnShippingByProfileId(LocalDateTime.now().minusMonths(3), principal.profileId());
        Long orderCount = ordersRepository.countOrderByProfileId(LocalDateTime.now().minusMonths(3), principal.profileId());
        Long productLikeCount = productLikeRepository.countByProfile_Id(principal.profileId());
        Long frequentlyOrderedProductCount = productRepository.countFrequentOrderProduct(LocalDateTime.now().minusMonths(12), 3, principal.profileId());

        return MyPageCountResponse.of(shippingCount, orderCount, productLikeCount,  frequentlyOrderedProductCount);
    }

}
