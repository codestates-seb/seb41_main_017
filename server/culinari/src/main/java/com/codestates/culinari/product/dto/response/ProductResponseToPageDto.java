package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.Product} entity
 */
@Builder
public record ProductResponseToPageDto(
        Long id,
        String name,
        BigDecimal price
) implements Serializable {

    public static ProductResponseToPageDto of(Long id, String name, BigDecimal price){
        return new ProductResponseToPageDto(id, name, price);
    }

    public static ProductResponseToPageDto from(ProductDto dto){
        return new ProductResponseToPageDto(
                dto.id(),
                dto.name(),
                dto.price()
        );
    }
}