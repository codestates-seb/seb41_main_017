package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.dto.ProductDto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.CategoryDetail} entity
 */
public record CategoryDetailResponseDto(
        Long id,
        String name,
        String categoryDetailCode,
        List<ProductResponseToPageDto> products

) implements Serializable {

    public static CategoryDetailResponseDto of(Long id, String name, String categoryDetailCode, List<ProductResponseToPageDto> products){
        return new CategoryDetailResponseDto(id, name, categoryDetailCode, products);
    }
    public static CategoryDetailResponseDto from(CategoryDetailDto dto){
        return new CategoryDetailResponseDto(
                dto.id(),
                dto.name(),
                dto.categoryDetailCode(),
                dto.productDtos().stream()
                        .map(product -> ProductResponseToPageDto
                                .builder()
                                .id(product.id())
                                .name(product.name())
                                .price(product.price())
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
