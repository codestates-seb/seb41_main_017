package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductImageDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponseToPage(
        Long id,
        String name,
        String brand,
        BigDecimal price,
        List<ProductImageDto> productImageDtos
        )
{

    public static ProductResponseToPage of(Long id, String name,String brand,BigDecimal price,List<ProductImageDto> productImageDtos){
        return new ProductResponseToPage(id, name,brand, price,productImageDtos);
    }

    public static ProductResponseToPage from(ProductDto dto){
        return new ProductResponseToPage(
                dto.id(),
                dto.name(),
                dto.brand(),
                dto.price(),
                dto.productImageDtos()
        );
    }
}
