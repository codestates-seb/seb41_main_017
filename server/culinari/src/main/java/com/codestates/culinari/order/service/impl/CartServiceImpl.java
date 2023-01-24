package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.order.dto.CartDto;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import com.codestates.culinari.order.dto.response.CartResponse;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.repository.CartRepository;
import com.codestates.culinari.order.service.CartService;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void createCart(CartPost post, CustomPrincipal principal) {
        verifyPrincipal(principal);

        Product product = productRepository.findById(post.productId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.PRODUCT_NOT_FOUND));
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        CartDto dto = post.toDto();

        cartRepository.findByProfile_IdAndProduct_Id(profile.getId(), product.getId())
                .ifPresentOrElse(
                        cart -> {
                            cart.updateQuantity(cart.getQuantity() + dto.quantity());
                            cartRepository.save(cart);
                        }, () -> {
                            cartRepository.save(dto.toEntity(profile, product));
                        }
                );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CartResponse> readCarts(Pageable pageable, CustomPrincipal principal) {
        verifyPrincipal(principal);

        return cartRepository.findAllByProfile_Id(pageable, principal.profileId()).map(CartResponse::from);
    }

    @Override
    public void updateCart(CartPatch patch, Long cartId, CustomPrincipal principal) {
        verifyPrincipal(principal);

        Cart cart = cartRepository.findByIdAndProfile_Id(cartId, principal.profileId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CART_NOT_FOUND));

        CartDto dto = patch.toDto();
        cart.updateQuantity(dto.quantity());
    }

    @Override
    public void deleteCart(Long cartId, CustomPrincipal principal) {
        verifyPrincipal(principal);

        if(cartRepository.existsByIdAndProfile_Id(cartId, principal.profileId()))
            cartRepository.deleteById(cartId);
        else
            throw new BusinessLogicException(ExceptionCode.CART_NOT_FOUND);
    }

    public void verifyPrincipal(CustomPrincipal principal) {
        if (principal == null) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }
}
