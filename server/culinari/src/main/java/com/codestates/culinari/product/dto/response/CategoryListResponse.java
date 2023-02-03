package com.codestates.culinari.product.dto.response;

import com.codestates.culinari.product.entitiy.Category;

public record CategoryListResponse(
        Long id,
        String name,
        String categoryCode
) {

    public static CategoryListResponse of(Long id, String name, String categoryCode){
        return new CategoryListResponse(id, name, categoryCode);
    }
    public static CategoryListResponse from(Category entity){
        return new CategoryListResponse(
                entity.getId(),
                entity.getName(),
                entity.getCategoryCode()
        );
    }
}
