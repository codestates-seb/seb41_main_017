package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;

public interface ProductCsService {

    public ProductInquiryDto createProductInquiry(ProductInquiryRequest productInquiryRequest, Long productId);

    public ProductReviewDto createProductInquiry(ProductReviewRequest productInquiryRequest, Long productId);

    public ProductInquiryDto updateProductInquiry(ProductInquiryRequest productInquiryRequest, Long productInquiryId);
    public ProductReviewDto updateProductReview(ProductReviewRequest productReviewRequest, Long productReviewId);

    public void deleteProductInquiry(Long productInquiryId);

    public void deleteProductReview(Long productReviewId);
}
