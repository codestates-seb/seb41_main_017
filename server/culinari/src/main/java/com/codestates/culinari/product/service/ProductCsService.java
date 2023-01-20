package com.codestates.culinari.product.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductCsService {
    public List<ProductInquiryDto> readProductInquiry(CustomPrincipal principal);
    // 문의 작성
    void createProductInquiry(ProductInquiryRequest productInquiryRequest, CustomPrincipal principal, Long productId);
    // 후기 작성
    void createProductReview(ProductReviewRequest productReviewRequest, CustomPrincipal principal, Long productId, List<MultipartFile> multipartFiles) throws IOException;
    void saveProductReviewImages(Long productReviewId, List<MultipartFile> multipartFiles) throws IOException;
    //문의 수정
    void updateProductInquiry(ProductInquiryRequest productInquiryRequest, CustomPrincipal principal, Long productInquiryId);
    //리뷰 수정
    void updateProductReview(ProductReviewRequest productReviewRequest, CustomPrincipal principal, Long productReviewId);
    //문의 삭제
    void deleteProductInquiry(CustomPrincipal principal, Long productInquiryId);
    //리뷰 삭제
    void deleteProductReview(CustomPrincipal principal, Long productReviewId);
    void updateLike(ProductReviewLikeRequest productReviewLikePost, CustomPrincipal principal, Long productReviewId);
}
