package com.codestates.culinari.product.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.dto.ProductLikeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    //찜 등록
    void createProductLike(Long productId, CustomPrincipal principal);

    //찜 조회
    @Transactional(readOnly = true)
    Page<ProductLikeDto> readProductLike(CustomPrincipal principal, Pageable pageable);
    //찜 삭제
    void deleteProductLike(Long productId, CustomPrincipal principal);
    public ProductDto readProduct(Long productId);
    //통합 검색 (Name, Seller, Brand)
    Page<ProductDto> readProductWithKeyWord(String keyWord, Pageable pageable);
    public Page<ProductDto> readProductWithSortedType(String filter, Pageable pageable);
    public Page<ProductDto> readProductWithCategoryCode(String categoryCode,String sortedType, Pageable pageable);
}
