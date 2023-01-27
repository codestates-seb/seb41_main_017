package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.dto.response.ProductResponseToPage;
import com.codestates.culinari.product.entitiy.CategoryDetail;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryDetailDto(
        Long id,
        String categoryCode,
        String name,
        String categoryDetailCode,
        List<ProductResponseToPage> productDtos


)
{

    public static CategoryDetailDto of(Long id, String categoryCode, String name, String categoryDetailCode,List<ProductResponseToPage> productDtos) {
        return new CategoryDetailDto(id, categoryCode, name, categoryDetailCode,productDtos);
    }

    public static CategoryDetailDto from(CategoryDetail entity) {
        return new CategoryDetailDto(
                entity.getId(),
                entity.getCategory().getCategoryCode(),
                entity.getName(),
                entity.getCategoryDetailCode(),
                entity.getProducts().stream()
                        .map(
                                product -> ProductResponseToPage
                                .builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .build())
                        .collect(Collectors.toList())
        );
    }
    public CategoryDetail toEntity(){
        return CategoryDetail.of(
                name,
                categoryDetailCode
        );
    }
}
