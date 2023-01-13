package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import com.codestates.culinari.product.entitiy.Product;
import com.codestates.culinari.product.entitiy.ProductInquiry;
import com.codestates.culinari.product.entitiy.ProductReview;
import com.codestates.culinari.product.entitiy.ProductReviewLike;
import com.codestates.culinari.product.repository.ProductInquiryRepository;
import com.codestates.culinari.product.repository.ProductRepository;
import com.codestates.culinari.product.repository.ProductReviewLikeRepository;
import com.codestates.culinari.product.repository.ProductReviewRepository;
import com.codestates.culinari.product.service.ProductCsService;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductCsServiceImpl implements ProductCsService {
    private final ProductRepository productRepository;
    private final ProductInquiryRepository productInquiryRepository;
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewLikeRepository productReviewLikeRepository;
    private final ProfileRepository profileRepository;

    // profileId로 문의 리스트 get
    @Transactional(readOnly = true)
    public List<ProductInquiryDto> readProductInquiry(CustomPrincipal principal){

        return productInquiryRepository.findByProfile_Id(principal.profileId())
                .stream()
                .map(ProductInquiryDto::from)
                .collect(Collectors.toList());
    }

    // 문의 작성
    @Override
    public void createProductInquiry(ProductInquiryRequest productInquiryRequest, CustomPrincipal principal, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        ProductInquiry productInquiry = ProductInquiry.of(productInquiryRequest.title(), productInquiryRequest.content(), product, profile);
        productInquiryRepository.save(productInquiry);
    }

    // 후기 작성
    @Override
    public void createProductReview(ProductReviewRequest productReviewRequest, CustomPrincipal principal, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        ProductReview productReview = ProductReview.of(productReviewRequest.title(), productReviewRequest.content(), product, profile);
        ProductReviewLike productReviewLike = productReviewLikeRepository.save(ProductReviewLike.of(0L,productReview));
        productReviewRepository.save(productReview);
    }
    //문의 수정
    @Override
    public void updateProductInquiry(ProductInquiryRequest productInquiryRequest, CustomPrincipal principal, Long productInquiryId) {
        ProductInquiry productInquiry = productInquiryRepository.findById(productInquiryId).orElseThrow(() -> new EntityNotFoundException("문의가 없습니다"));
        if (productInquiryRequest.title() != null) {
            productInquiry.setTitle(productInquiryRequest.title());
        }
        if (productInquiryRequest.content() != null) {
            productInquiry.setContent(productInquiryRequest.content());
        }
    }
    //리뷰 수정
    @Override
    public void updateProductReview(ProductReviewRequest productReviewRequest, CustomPrincipal principal, Long productReviewId) {
        ProductReview productReview = productReviewRepository.findById(productReviewId).orElseThrow(() -> new EntityNotFoundException("문의가 없습니다"));
        if (productReviewRequest.title() != null) {
            productReview.setTitle(productReviewRequest.title());
        }
        if (productReviewRequest.content() != null) {
            productReview.setContent(productReviewRequest.content());
        }
    }

    //문의 삭제
    @Override
    public void deleteProductInquiry(CustomPrincipal principal, Long productInquiryId){
        productInquiryRepository.deleteById(productInquiryId);
    }

    //리뷰 삭제
    @Override
    public void deleteProductReview(CustomPrincipal principal,Long productReviewId){
        productReviewRepository.deleteById(productReviewId);
    }

    @Override
    public void updateLike(ProductReviewLikeRequest productReviewLikePost, CustomPrincipal principal, Long productReviewId){

        ProductReview productReview = productReviewRepository.findById(productReviewId).orElseThrow(() -> new EntityNotFoundException("리뷰가 없습니다"));

        Profile profile = profileRepository.getReferenceById(principal.profileId());

        ProductReviewLike productReviewLike = productReviewLikeRepository.getReferenceById(productReviewId);
        productReviewLike.setLikeNum(productReviewLike.getLikeNum() + productReviewLikePost.like());
        productReviewLike.setProductReviewProfileIds(principal.profileId());
    }
}
