package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record ProductResponseToPage(
        Long id,
        String name,
        BigDecimal price
)
{

    public static ProductResponseToPage of(Long id, String name, BigDecimal price){
        return new ProductResponseToPage(id, name, price);
    }

    public static ProductResponseToPage from(ProductDto dto){
        return new ProductResponseToPage(
                dto.id(),
                dto.name(),
                dto.price()
        );
    }
}
