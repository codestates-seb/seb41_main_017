package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import com.codestates.culinari.product.entitiy.Category;

import java.util.List;
import java.util.stream.Collectors;


public record CategoryWithDetailListResponse(
        Long id,
        String name,
        String categoryCode,
        List<CategoryDetailResponse> categoryDetails

)
{

    public static CategoryWithDetailListResponse of(Long id, String name, String categoryCode, List<CategoryDetailResponse> categoryDetailDtos){
        return new CategoryWithDetailListResponse(id, name, categoryCode,categoryDetailDtos);
    }

    public static CategoryWithDetailListResponse from(Category entity){
        return new CategoryWithDetailListResponse(
                entity.getId(),
                entity.getName(),
                entity.getCategoryCode(),
                entity.getCategoryDetails().stream()
                        .map(CategoryDetailResponse::from)
                        .collect(Collectors.toList())
        );
    }
}
