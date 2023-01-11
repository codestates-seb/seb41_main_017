package com.codestates.culinari.order.dto;

import com.codestates.culinari.order.entitiy.Cart;
import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.user.dto.ProfileDto;
import com.codestates.culinari.user.entitiy.Profile;

import java.time.LocalDateTime;

public record CartDto (
        Long id,
        Integer quantity,
        ProfileDto profileDto,
        ProductDto productDto,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
){

    public static CartDto of(Integer quantity) {
        return new CartDto(null, quantity, null, null, null, null, null, null);
    }

    public static CartDto of(Long id, Integer quantity, ProfileDto profileDto, ProductDto productDto, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new CartDto(id, quantity, profileDto, productDto, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static CartDto from(Cart entity) {
        return CartDto.of(
                entity.getId(),
                entity.getQuantity(),
                ProfileDto.from(entity.getProfile()),
                ProductDto.from(entity.getProduct()),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }

    public Cart toEntity(Profile profile, Product product) {
        return Cart.of(
                quantity,
                profile,
                product
        );
    }
}
