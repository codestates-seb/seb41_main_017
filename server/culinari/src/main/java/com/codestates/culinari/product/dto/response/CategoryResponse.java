package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.dto.CategoryDto;

import java.io.Serializable;
import java.util.List;


public record CategoryResponse(
        Long id,
        String name,
        String categoryCode,
        List<CategoryDetailDto> categoryDetailDtos

)
{

    public static CategoryResponse of(Long id, String name, String categoryCode, List<CategoryDetailDto> categoryDetailDtos){
        return new CategoryResponse(id, name, categoryCode,categoryDetailDtos);
    }

    public static CategoryResponse from(CategoryDto dto){
        return new CategoryResponse(
                dto.id(),
                dto.name(),
                dto.categoryCode(),
                dto.categoryDetailDtos()
        );
    }
}
