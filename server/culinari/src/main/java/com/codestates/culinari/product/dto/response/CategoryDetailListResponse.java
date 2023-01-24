package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.entitiy.CategoryDetail;

public record CategoryDetailListResponse(
        Long id,
        String name,
        String categoryDetailCode
) {

    public static CategoryDetailListResponse of(Long id, String name, String categoryCode){
        return new CategoryDetailListResponse(id, name, categoryCode);
    }
    public static CategoryDetailListResponse from(CategoryDetail entity){
        return new CategoryDetailListResponse(
                entity.getId(),
                entity.getName(),
                entity.getCategoryDetailCode()
        );
    }
}
