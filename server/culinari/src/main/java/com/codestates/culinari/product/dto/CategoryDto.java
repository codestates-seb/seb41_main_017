package com.codestates.culinari.product.dto;

import com.codestates.culinari.product.entitiy.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryDto(

        Long id,
        String name,
        String categoryCode,

        List<CategoryDetailDto> categoryDetailDtos
)
{


    public static CategoryDto of(Long id, String name, String categoryCode, List<CategoryDetailDto> categoryDetailDtos){
        return new CategoryDto(id, name, categoryCode, categoryDetailDtos);
    }

    public static CategoryDto from(Category entity){
        return new CategoryDto(
                entity.getId(),
                entity.getName(),
                entity.getCategoryCode(),
                entity.getCategoryDetails().stream()
                        .map(CategoryDetailDto::from)
                        .collect(Collectors.toList())
        );
    }
    public Category toEntity(){
        return Category.of(
                name,
                categoryCode
        );
    }
}
