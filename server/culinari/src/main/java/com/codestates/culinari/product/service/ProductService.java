package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface ProductService {
    public ProductDto readProduct(Long productId);

    public Page<ProductDto> readProductWithSortedType(String filter, int page, int size);

    public Page<ProductDto> readProductWithCategoryCode(String categoryCode,String sortedType, int page, int size);
}
