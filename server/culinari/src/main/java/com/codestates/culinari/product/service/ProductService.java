package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public ProductDto readProduct(Long productId);
    public Page<ProductDto> readProductWithSortedType(String filter, Pageable pageable);
    public Page<ProductDto> readProductWithCategoryCode(String categoryCode,String sortedType, Pageable pageable);
}
