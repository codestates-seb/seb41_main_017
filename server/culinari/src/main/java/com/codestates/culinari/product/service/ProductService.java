package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public ProductDto readProduct(Long productId);

    public Page<ProductDto> readProductWithFilter(String filter, Pageable pageable);
}
