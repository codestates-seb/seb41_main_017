package com.codestates.culinari.order.repository;

import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.order.repository.querydsl.CartRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {

    Page<Cart> findAllByProfile_Id(Pageable pageable, Long profileId);

    Optional<Cart> findByProfile_IdAndProduct_Id(Long productId, Long profileId);

    Optional<Cart> findByIdAndProfile_Id(Long cartId, Long profileId);

    boolean existsByIdAndProfile_Id(Long cartId, Long profileId);
}
