package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.CategoryDto;
import com.codestates.culinari.product.dto.CategoryDetailDto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.codestates.culinari.product.entitiy.Category} entity
 */
public record CategoryResponseDto(
        Long id,
        String name,
        String categoryCode,
        List<CategoryDetailDto> categoryDetailDtos

) implements Serializable {

    public static CategoryResponseDto of(Long id, String name, String categoryCode,List<CategoryDetailDto> categoryDetailDtos){
        return new CategoryResponseDto(id, name, categoryCode,categoryDetailDtos);
    }

    public static CategoryResponseDto from(CategoryDto dto){
        return new CategoryResponseDto(
                dto.id(),
                dto.name(),
                dto.categoryCode(),
                dto.categoryDetailDtos()
        );
    }
}
