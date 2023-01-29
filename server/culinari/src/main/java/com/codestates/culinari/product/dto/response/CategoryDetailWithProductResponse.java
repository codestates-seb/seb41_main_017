package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.CategoryDetailDto;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryDetailWithProductResponse(
        Long id,
        String name,
        String categoryDetailCode,
        List<ProductResponseToPage> products

)
{

    public static CategoryDetailWithProductResponse of(Long id, String name, String categoryDetailCode, List<ProductResponseToPage> products){
        return new CategoryDetailWithProductResponse(id, name, categoryDetailCode, products);
    }
    public static CategoryDetailWithProductResponse from(CategoryDetailDto dto){
        return new CategoryDetailWithProductResponse(
                dto.id(),
                dto.name(),
                dto.categoryDetailCode(),
                dto.productDtos().stream()
                        .map(
                                product -> ProductResponseToPage
                                .builder()
                                .id(product.id())
                                .name(product.name())
                                .price(product.price())
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
