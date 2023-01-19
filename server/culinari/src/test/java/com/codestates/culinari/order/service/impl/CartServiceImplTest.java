package com.codestates.culinari.order.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.order.dto.request.CartPatch;
import com.codestates.culinari.order.dto.request.CartPost;
import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.repository.CartRepository;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.user.repository.ProfileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static com.codestates.culinari.order.Stub.Stub.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 장바구니")
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl sut;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private CartRepository cartRepository;

    @DisplayName("장바구니에 담을 상품 정보와 수량을 입력하면, 장바구니를 등록한다.")
    @Test
    void givenNewCartInfoAndPrincipal_whenSavingCart_thenSaveCart() {
        // Given
        CartPost post = createCartPost(1L, 1000);
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(productRepository.findById(anyLong())).willReturn(Optional.of(createProduct(1L)));
        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
        given(cartRepository.findByProfile_IdAndProduct_Id(anyLong(), anyLong())).willReturn(Optional.empty());
        given(cartRepository.save(any(Cart.class))).willReturn(any(Cart.class));

        // When
        sut.createCart(post, principal);

        // Then
        then(productRepository).should().findById(anyLong());
        then(profileRepository).should().getReferenceById(anyLong());
        then(cartRepository).should().findByProfile_IdAndProduct_Id(anyLong(), anyLong());
        then(cartRepository).should().save(any(Cart.class));
    }

    @DisplayName("존재하는 장바구니를 추가하려하면, 장바구니의 상품 수량을 수정한다.")
    @Test
    void givenExistentCartInfoAndPrincipal_whenSavingCart_thenUpdateCart() {
        // Given
        CartPost post = createCartPost(1L, 1000);
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(productRepository.findById(anyLong())).willReturn(Optional.of(createProduct(1L)));
        given(profileRepository.getReferenceById(anyLong())).willReturn(createProfile(1L));
        given(cartRepository.findByProfile_IdAndProduct_Id(anyLong(), anyLong())).willReturn(Optional.of(createCart(1L, post.quantity(), 1L, 1L)));
        given(cartRepository.save(any(Cart.class))).willReturn(any(Cart.class));

        // When
        sut.createCart(post, principal);

        // Then
        then(productRepository).should().findById(anyLong());
        then(profileRepository).should().getReferenceById(anyLong());
        then(cartRepository).should().findByProfile_IdAndProduct_Id(anyLong(), anyLong());
        then(cartRepository).should().save(any(Cart.class));
    }

    @DisplayName("존재하지 않는 상품 정보 입력하면, 예외를 던진다.")
    @Test
    void givenNonexistentCartInfo_whenSavingCart_thenThrowsException() {
        // Given
        CartPost post = createCartPost(1000L, 1000);
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(productRepository.findById(anyLong())).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.createCart(post, principal));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Product not found");
        then(productRepository).should().findById(anyLong());
    }

    @DisplayName("페이지 요청 정보를 입력하면, 회원의 장바구니 페이지를 반환한다.")
    @Test
    void givenPageInfoAndPrincipal_whenSearchingCart_thenReturnCartPage() {
        // Given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(cartRepository.findAllByProfile_Id(pageable, principal.profileId())).willReturn(Page.empty());

        // When
        sut.readCarts(pageable, principal);

        // Then
        then(cartRepository).should().findAllByProfile_Id(any(Pageable.class), anyLong());
    }

    @DisplayName("장바구니의 수정정보를 입력하면, 장바구니를 수정한다.")
    @Test
    void givenModifiedCartInfoAndPrincipal_whenUpdatingCart_thenUpdatesCart() {
        // Given
        Long cartId = 1L;
        CartPatch patch = createCartPatch(3);
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(cartRepository.findByIdAndProfile_Id(anyLong(), anyLong())).willReturn(Optional.of(createCart(cartId, patch.quantity(), 1L, 1L)));

        // When
        sut.updateCart(patch, cartId, principal);

        // Then
        then(cartRepository).should().findByIdAndProfile_Id(anyLong(), anyLong());
    }

    @DisplayName("없는 장바구니의 수정정보를 입력하면, 예외를 던진다.")
    @Test
    void givenNonexistentModifiedCartInfo_whenUpdatingCart_thenThrowsException() {
        // Given
        Long cartId = 10000L;
        CartPatch patch = createCartPatch(3);
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(cartRepository.findByIdAndProfile_Id(anyLong(), anyLong())).willReturn(Optional.empty());

        // When
        Throwable t = catchThrowable(() -> sut.updateCart(patch, cartId, principal));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Cart not found");
        then(cartRepository).should().findByIdAndProfile_Id(anyLong(), anyLong());
    }

    @DisplayName("장바구니의 Id 입력하면, 장바구니를 삭제한다.")
    @Test
    void givenCartIdAndPrincipal_whenDeletingCart_thenDeletesCart() {
        // Given
        Long cartId = 1L;
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(cartRepository.existsByIdAndProfile_Id(anyLong(), anyLong())).willReturn(true);
        willDoNothing().given(cartRepository).deleteById(anyLong());

        // When
        sut.deleteCart(cartId, principal);

        // Then
        then(cartRepository).should().existsByIdAndProfile_Id(anyLong(), anyLong());
        then(cartRepository).should().deleteById(anyLong());
    }

    @DisplayName("없는 장바구니를 삭제하려 입력하면, 예외를 던진다.")
    @Test
    void givenNonexistentCartId_whenDeletingCart_thenThrowsException() {
        // Given
        Long cartId = 1L;
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);

        given(cartRepository.existsByIdAndProfile_Id(anyLong(), anyLong())).willReturn(false);

        // When
        Throwable t = catchThrowable(() -> sut.deleteCart(cartId, principal));

        // Then
        assertThat(t)
                .isInstanceOf(BusinessLogicException.class)
                .hasMessage("Cart not found");
        then(cartRepository).should().existsByIdAndProfile_Id(anyLong(), anyLong());
    }
}
