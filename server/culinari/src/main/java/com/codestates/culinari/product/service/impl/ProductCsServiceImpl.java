package com.codestates.culinari.product.service.impl;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.global.exception.BusinessLogicException;
import com.codestates.culinari.global.exception.ExceptionCode;
import com.codestates.culinari.global.file.S3Uploader;
import com.codestates.culinari.order.entitiy.OrderDetail;
import com.codestates.culinari.order.repository.OrderDetailRepository;
import com.codestates.culinari.product.dto.ProductInquiryDto;
import com.codestates.culinari.product.dto.request.ProductInquiryRequest;
import com.codestates.culinari.product.dto.request.ProductReviewLikeRequest;
import com.codestates.culinari.product.dto.request.ProductReviewRequest;
import com.codestates.culinari.product.entitiy.*;
import com.codestates.culinari.product.repository.*;
import com.codestates.culinari.product.service.ProductCsService;
import com.codestates.culinari.user.dto.response.ProfileMyPageReviewExistResponse;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ProductCsServiceImpl implements ProductCsService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final ProductInquiryRepository productInquiryRepository;
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewLikeRepository productReviewLikeRepository;
    private final ProfileRepository profileRepository;
    private final ProductReviewImageRepository productReviewImageRepository;
    private final S3Uploader s3Uploader;

    // profileId로 문의 리스트 get
    @Transactional(readOnly = true)
    public List<ProductInquiryDto> readProductInquiry(CustomPrincipal principal){

        return productInquiryRepository.findByProfile_Id(principal.profileId())
                .stream()
                .map(ProductInquiryDto::from)
                .collect(Collectors.toList());
    }
    //profileId로 리뷰 리스트 get
    @Transactional(readOnly = true)
    @Override
    public Page<ProfileMyPageReviewExistResponse> readProductReview(CustomPrincipal principal, Pageable pageable){
        return productReviewRepository.findByProfileId(principal.profileId(), PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), Sort.by("id").descending())).map(ProfileMyPageReviewExistResponse::from);
    }

    // 문의 작성
    @Override
    public void createProductInquiry(ProductInquiryRequest productInquiryRequest, CustomPrincipal principal, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        ProductInquiry productInquiry = ProductInquiry.of(productInquiryRequest.title(), productInquiryRequest.content(), product, profile);
        productInquiryRepository.save(productInquiry);
    }
    // 상품 문의 호출
    @Override
    public Page<ProductInquiry> readProductInquiry(Long productId, Pageable pageable){
        return productInquiryRepository.findByProductId(productId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()));
    }

    // 상품 리뷰 호출
    @Override
    public Page<ProductReview> readProductReviewWithSortedType(String sortedType,Long productId, Pageable pageable){
        if(sortedType.equals("lower"))
        return productReviewRepository.findByProductId(productId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("reviewStar")));
        else if (sortedType.equals("higher")) {
            return productReviewRepository.findByProductId(productId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("reviewStar").descending()));
        }
        return productReviewRepository.findByProductId(productId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()));
    }

    // 후기 작성
    @Override
    public void createProductReview(ProductReviewRequest productReviewRequest, CustomPrincipal principal, Long productId, Long orderId, List<MultipartFile> multipartFiles) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("상품이 없습니다"));
        Profile profile = profileRepository.getReferenceById(principal.profileId());
        OrderDetail orderDetail = orderDetailRepository.findByIdAndOrders_Profile_Id(orderId,principal.profileId());
        ProductReview productReview = ProductReview.of( productReviewRequest.content(), productReviewRequest.reviewStar(),product, profile);
        List<String> image = s3Uploader.uploads(multipartFiles);
        List<String> imgList = new ArrayList<>();
        for(String imageUrl : image){
            ProductReviewImage img  = new ProductReviewImage(imageUrl, productReview);
            productReviewImageRepository.save(img);
            imgList.add(img.getImgUrl());
        }
//        List<ProductReviewImage> imageList = fileStore.storeReviewImages(multipartFiles);
//        imageList.stream().forEach(productReviewImage -> productReviewImage.(productReview));
        ProductReviewLike productReviewLike = productReviewLikeRepository.save(ProductReviewLike.of(0L,productReview));
        productReview.setProductReviewLike(productReviewLike);
        productReview.setOrderDetail(orderDetail);
        orderDetail.setProductReview(productReview);
        productReviewRepository.saveAndFlush(productReview);
    }

    //후기 사진 불러오기
   /*
    @Transactional(readOnly = true)
    @Override
    public List<ProductReviewImage> findProductReviewImages(){
        List<ProductReviewImage> images = ProductReviewImageRepository.findAll();
        return images;
    }*/
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
        if (productReviewRequest.content() != null) {
            productReview.setContent(productReviewRequest.content());
        }
    }

    //문의 삭제
    @Override
    public void deleteProductInquiry(CustomPrincipal principal, Long productInquiryId){

        ProductInquiry productInquiry = productInquiryRepository.getReferenceById(productInquiryId);

        if(!Objects.equals(productInquiry.getProfile().getId(), principal.profileId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        ProductInquiry productInquiry2 = productInquiryRepository.findById(productInquiryId)
                .filter(d -> Objects.equals(d.getProfile().getId(), principal.profileId()))
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND));


        productInquiryRepository.deleteById(productInquiryId);
    }

    //리뷰 삭제
    @Override
    public void deleteProductReview(CustomPrincipal principal,Long productId, Long productReviewId){
        OrderDetail orderDetail = orderDetailRepository.findByProductIdAndProductReviewIdAndProductReviewIsNotNull(productId, productReviewId);
        orderDetail.setProductReview(null);
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
