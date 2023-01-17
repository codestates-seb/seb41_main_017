package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    public ProductDto readProduct(Long productId);

    //통합 검색 (Name, Seller, Brand)
    @Transactional(readOnly = true)
    Page<ProductDto> readProductWithKeyWord(String keyWord, Pageable pageable);

    public Page<ProductDto> readProductWithSortedType(String filter, Pageable pageable);
    public Page<ProductDto> readProductWithCategoryCode(String categoryCode,String sortedType, Pageable pageable);
}
