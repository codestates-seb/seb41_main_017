package com.codestates.culinari.product.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.ProductReviewDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;

import java.util.List;

public interface ProductCsService {
    public List<ProductInquiryDto> readProductInquiry(CustomPrincipal principal);
    public ProductInquiryDto createProductInquiry(ProductInquiryRequest productInquiryRequest, CustomPrincipal principal , Long productId);
    public ProductReviewDto createProductReview(ProductReviewRequest productReviewRequest, CustomPrincipal principal, Long productId);
    public ProductInquiryDto updateProductInquiry(ProductInquiryRequest productInquiryRequest, Long productInquiryId);
    public ProductReviewDto updateProductReview(ProductReviewRequest productReviewRequest, Long productReviewId);
    public void deleteProductInquiry(Long productInquiryId);
    public void deleteProductReview(Long productReviewId);
}
