package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.repository.ProductInquiryRepository;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.repository.ProductReviewRepository;
import com.codestates.culinari.product.service.ProductCsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class ProductCsServiceImpl implements ProductCsService {
    private final ProductRepository productRepository;
    private final ProductInquiryRepository productInquiryRepository;
    private final ProductReviewRepository productReviewRepository;

    public ProductCsServiceImpl(
            ProductRepository productRepository,
            ProductInquiryRepository productInquiryRepository,
            ProductReviewRepository productReviewRepository) {
        this.productRepository = productRepository;
        this.productInquiryRepository = productInquiryRepository;
        this.productReviewRepository = productReviewRepository;
    }


    // 문의 작성
    public ProductInquiryDto createProductInquiry(ProductInquiryRequest productInquiryRequest, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));

        ProductInquiry productInquiry = productInquiryRepository.save(productInquiryRequest.toDto(product).toEntity(product));

        return ProductInquiryDto.from(productInquiry);
    }

    // 후기 작성
    public ProductReviewDto createProductInquiry(ProductReviewRequest productInquiryRequest, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));

        ProductReview productReview = productReviewRepository.save(productInquiryRequest.toDto(product).toEntity(product));

        return ProductReviewDto.from(productReview);
    }

    //문의 삭제
    public void deleteProductInquiry(Long productInquiryId){
        productInquiryRepository.deleteById(productInquiryId);
    }

    //리뷰 삭제
    public void deleteProductReview(Long productReviewId){
        productReviewRepository.deleteById(productReviewId);
    }
}
