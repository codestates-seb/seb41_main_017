package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //ID 상품 조회
    @Transactional(readOnly = true)
    public ProductDto readProduct(Long productId){
        return productRepository.findById(productId)
                .map(ProductDto::from)
                .orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> readProductWithSortedType(String sortedType, Pageable pageable){
        if(sortedType.equals("lower"))
            return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price")))
                    .map(ProductDto::from);
        else if(sortedType.equals("higher"))
            return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price").descending()))
                    .map(ProductDto::from);
        return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()))
                .map(ProductDto::from);
    }
}
