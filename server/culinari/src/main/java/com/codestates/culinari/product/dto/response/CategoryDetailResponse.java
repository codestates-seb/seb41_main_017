package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.dto.CategoryDetailDto;
import java.util.List;

public record CategoryDetailResponse(
        Long id,
        String name,
        String categoryDetailCode

)
{

    public static CategoryDetailResponse of(Long id, String name, String categoryDetailCode){
        return new CategoryDetailResponse(id, name, categoryDetailCode);
    }
    public static CategoryDetailResponse from(CategoryDetail entity){
        return new CategoryDetailResponse(
                entity.getId(),
                entity.getName(),
                entity.getCategoryDetailCode()
        );
    }
}
