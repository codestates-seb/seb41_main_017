package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.ProductDto;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    //신상품 조회
    @Transactional(readOnly = true)
    public Page<ProductDto> readProductWithSortedType(String sortedType, int page, int size){
        if(sortedType.equals("lower"))
            return productRepository.findAll(PageRequest.of(page,size, Sort.by("price")))
                    .map(ProductDto::from);
        else if(sortedType.equals("higher"))
            return productRepository.findAll(PageRequest.of(page,size, Sort.by("price").descending()))
                    .map(ProductDto::from);
        return productRepository.findAll(PageRequest.of(page,size, Sort.by("id").descending()))
                .map(ProductDto::from);
    }

    //카테고리 조회
    @Transactional(readOnly = true)
    public Page<ProductDto> readProductWithCategoryCode(String categoryCode,String sortedType, int page, int size){
        if(categoryCode.length() <=3 && sortedType.equals(("lower"))) return productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode, PageRequest.of(page, size, Sort.by("price"))).map(ProductDto::from);
        if(categoryCode.length() <=3 && sortedType.equals(("higher"))) return productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode, PageRequest.of(page, size, Sort.by("price").descending())).map(ProductDto::from);
        if(categoryCode.length() <=3 && sortedType.equals(("newest"))) return productRepository.findAllByCategoryDetailCategoryDetailCodeContaining(categoryCode, PageRequest.of(page, size, Sort.by("id").descending())).map(ProductDto::from);
        if(categoryCode.length() >3 && sortedType.equals(("lower"))) return productRepository.findAllByCategoryDetailCategoryDetailCode(categoryCode, PageRequest.of(page, size, Sort.by("price"))).map(ProductDto::from);
        if(categoryCode.length() >3 && sortedType.equals(("higher"))) return productRepository.findAllByCategoryDetailCategoryDetailCode(categoryCode, PageRequest.of(page, size, Sort.by("price").descending())).map(ProductDto::from);
        else return productRepository.findAllByCategoryDetailCategoryDetailCode(categoryCode, PageRequest.of(page, size, Sort.by("id").descending())).map(ProductDto::from);
    }
}
