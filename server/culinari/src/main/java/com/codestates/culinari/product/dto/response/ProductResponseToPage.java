package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponseToPage(
        Long id,
        String name,
        String brand,
        BigDecimal price
)
{

    public static ProductResponseToPage of(Long id, String name,String brand,BigDecimal price){
        return new ProductResponseToPage(id, name,brand, price);
    }

    public static ProductResponseToPage from(ProductDto dto){
        return new ProductResponseToPage(
                dto.id(),
                dto.name(),
                dto.brand(),
                dto.price()
        );
    }
}
