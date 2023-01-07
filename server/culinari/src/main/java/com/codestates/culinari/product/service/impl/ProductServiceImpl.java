package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDto readProduct(Long productId){
        return productRepository.findById(productId)
                .map(ProductDto::from)
                .orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
    }
}
