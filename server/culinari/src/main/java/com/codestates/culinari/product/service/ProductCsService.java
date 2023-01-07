package com.codestates.culinari.product.service;

import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;

public interface ProductCsService {

    public ProductInquiryDto createProductInquiry(ProductInquiryRequest productInquiryRequest, Long productId);
}
