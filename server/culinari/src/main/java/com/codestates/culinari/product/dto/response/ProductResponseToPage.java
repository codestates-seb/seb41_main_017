package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductImageDto;
import com.codestates.culinari.product.entitiy.Product;
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

    public static ProductResponseToPage from(Product entity){
        return new ProductResponseToPage(
                entity.getId(),
                entity.getName(),
                entity.getBrand(),
                entity.getPrice(),
                entity.getProductImages().stream()
                        .map(ProductImageDto::from)
                        .toList()
        );
    }
}
